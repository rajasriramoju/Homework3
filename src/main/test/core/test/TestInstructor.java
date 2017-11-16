package core.test;

import core.api.IInstructor;

import core.api.impl.Instructor;
import org.junit.Before;
import org.junit.Test;

import core.api.IStudent;
import core.api.impl.Student;
import core.api.IAdmin;
import core.api.impl.*;

import static org.junit.Assert.*;

/**
 * Created by Vincent on 23/2/2017.
 */
public class TestInstructor {

    private IInstructor instructor;
    private IStudent student;
    private IAdmin admin;
    

    @Before
    public void setup() {
        this.instructor = new Instructor();
        this.student = new Student();
        this.admin = new Admin();
    }

    @Test
    public void addHomeworkTest() {
      //(String instructorName, String className, int year, String homeworkName)
    	this.admin.createClass("Test", 2017, "Teacher", 15);
    	this.instructor.addHomework("Teacher", "Test", 2017, "hw1");
    	assertTrue(this.instructor.homeworkExists("Test", 2017, "hw1"));
    	
    } // valid case -> generic case

    @Test
    public void addHomeworkTest2() {
    	this.admin.createClass("Test", 2017, "Teacher", 15);
    	this.instructor.addHomework("Teacher2", "Test", 2017, "hw1");
    	assertFalse(this.instructor.homeworkExists("Test", 2017, "hw1"));
    	
    } // wrong instructor assigns homework
    
    @Test
    public void addHomeworkBlankHomework() {
    	this.admin.createClass("Test", 2017, "Teacher", 15);
    	this.instructor.addHomework("Teacher", "Test", 2017, "");
    	assertFalse(this.instructor.homeworkExists("Test", 2017, ""));
    	
    } // Empty string for homework
    
    @Test
    public void assignGradeTestTrue() {
    	this.admin.createClass("Test", 2017, "Teacher", 15);
    	this.instructor.addHomework("Teacher", "Test", 2017, "hw1");
    	this.student.registerForClass("Stud1", "Test", 2017);
    	this.student.submitHomework("Stud1", "hw1", "Answer", "Test", 2017);
    	this.instructor.assignGrade("Teacher", "Test", 2017, "hw1", "Stud1", 90);
    	assertNotNull(this.instructor.getGrade("Test", 2017, "hw1", "Stud1"));
    	//     Integer getGrade(String className, int year, String homeworkName, String studentName);
    	//void assignGrade(String instructorName, String className, int year, String homeworkName, String studentName, int grade);

    } //true case -> homework has been submitted and grade is present
    
    @Test
    public void assignGradeNoSubmission() {
    	this.admin.createClass("Test", 2017, "Teacher", 15);
    	this.instructor.addHomework("Teacher", "Test", 2017, "hw1");
    	this.student.registerForClass("Stud1", "Test", 2017);
    	//this.student.submitHomework("Stud1", "hw1", "Answer", "Test", 2017);
    	this.instructor.assignGrade("Teacher", "Test", 2017, "hw1", "Stud1", 90);
    	assertNull(this.instructor.getGrade("Test", 2017, "hw1", "Stud1"));
    	//     Integer getGrade(String className, int year, String homeworkName, String studentName);
    	//void assignGrade(String instructorName, String className, int year, String homeworkName, String studentName, int grade);
    	
    } //false case -> homework has not been submitted and grade is present
    
    @Test
    public void assignGradeTestNoHomework() {
    	this.admin.createClass("Test", 2017, "Teacher", 15);
    	//this.instructor.addHomework("Teacher", "Test", 2017, "hw1");
    	this.student.registerForClass("Stud1", "Test", 2017);
    	this.student.submitHomework("Stud1", "hw1", "Answer", "Test", 2017);
    	this.instructor.assignGrade("Teacher", "Test", 2017, "hw1", "Stud1", 90);
    	assertNull(this.instructor.getGrade("Test", 2017, "hw1", "Stud1"));
    	//     Integer getGrade(String className, int year, String homeworkName, String studentName);
    	//void assignGrade(String instructorName, String className, int year, String homeworkName, String studentName, int grade);

    } //false case -> homework has not been created but is submitted and there is a grade
    
    @Test
    public void assignGradeTestWrongTeacher() {
    	this.admin.createClass("Test", 2017, "Teacher", 15);
    	this.instructor.addHomework("Teacher2", "Test", 2017, "hw1");
    	this.student.registerForClass("Stud1", "Test", 2017);
    	this.student.submitHomework("Stud1", "hw1", "Answer", "Test", 2017);
    	this.instructor.assignGrade("Teacher", "Test", 2017, "hw1", "Stud1", 90);
    	assertNull(this.instructor.getGrade("Test", 2017, "hw1", "Stud1"));
    	//     Integer getGrade(String className, int year, String homeworkName, String studentName);
    	//void assignGrade(String instructorName, String className, int year, String homeworkName, String studentName, int grade);

    } //false case -> homework has been created by the wrong instructor 
    
    @Test
    public void assignGradeTestGrade0() {
    	this.admin.createClass("Test", 2017, "Teacher", 15);
    	this.instructor.addHomework("Teacher", "Test", 2017, "hw1");
    	this.student.registerForClass("Stud1", "Test", 2017);
    	this.student.submitHomework("Stud1", "hw1", "Answer", "Test", 2017);
    	this.instructor.assignGrade("Teacher", "Test", 2017, "hw1", "Stud1", 0);
    	assertNull(this.instructor.getGrade("Test", 2017, "hw1", "Stud1"));
    	//     Integer getGrade(String className, int year, String homeworkName, String studentName);
    	//void assignGrade(String instructorName, String className, int year, String homeworkName, String studentName, int grade);

    } //true case -> homework has been submitted and grade is present but 0
    
    @Test
    public void assignGradeTestGradeNegative() {
    	this.admin.createClass("Test", 2017, "Teacher", 15);
    	this.instructor.addHomework("Teacher", "Test", 2017, "hw1");
    	this.student.registerForClass("Stud1", "Test", 2017);
    	this.student.submitHomework("Stud1", "hw1", "Answer", "Test", 2017);
    	this.instructor.assignGrade("Teacher", "Test", 2017, "hw1", "Stud1", -1);
    	assertNull(this.instructor.getGrade("Test", 2017, "hw1", "Stud1"));
    	//     Integer getGrade(String className, int year, String homeworkName, String studentName);
    	//void assignGrade(String instructorName, String className, int year, String homeworkName, String studentName, int grade);

    } //false case -> homework grade is negative
    
    //label the test funcs as @Test
}



