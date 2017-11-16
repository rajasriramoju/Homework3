package core.test;

import core.api.IInstructor;

import org.junit.Before;
import org.junit.Test;

import core.api.IStudent;
import core.api.IAdmin;
import core.api.impl.*;

import static org.junit.Assert.*;

public class TestStudent {

	private IInstructor instructor;
    private IStudent student;
    private IAdmin admin;
    

    @Before
    public void setup() {
        this.instructor = new Instructor();
        this.student = new Student();
        this.admin = new Admin();
    }
    
    //-----------------All of the below are for classes that exist and registerForClass()----------------
    
    @Test
    public void registerForClassTestTrue() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("Student", "Test", 2017);
        assertTrue(this.student.isRegisteredFor("Student", "Test", 2017));
                
    } //Student is registered for class and capacity allows the student to enroll
	
    
    @Test
    public void registerForClassTestNoStudName() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("", "Test", 2017));
                
    } //Student is registered for class and capacity allows the student to enroll
    
    
    @Test
    public void registerForClassTestOverCapacity() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test", 2017, 1);
        this.student.registerForClass("Student1", "Test", 2017);
        this.student.registerForClass("Student2", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("Student2", "Test", 2017));
                
    } //2nd student cannot be enrolled in class
    
    
    //------All of the following are for cases where class has not been created and registerForClass()------
    
    @Test
    public void registerForClassTestNoClass() {
        //this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("Student", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("Student", "Test", 2017));
                
    } //Student is registered for class that doesn't exist
    
    
    //-------dropClass()---------
    
    @Test
    public void dropClassTestTrue() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("Student", "Test", 2017);
        this.student.dropClass("Student", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("Student", "Test", 2017));
                
    } //Student is registered for class and drops it and is valid
   
    public void dropClassTestNotRegistered() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        //this.student.registerForClass("Student", "Test", 2017);
        this.student.dropClass("Student", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("Student", "Test", 2017));
                
    } //Student is registered for class and drops it -> valid case
   
  
    @Test
    public void submitHomeworkTrue() {
    	this.admin.createClass("Test", 2017, "Teacher", 15);
    	this.instructor.addHomework("Teacher", "Test", 2017, "hw1");
    	this.student.registerForClass("Stud1", "Test", 2017);
    	this.student.submitHomework("Stud1", "hw1", "Answer", "Test", 2017);
    	assertTrue(this.student.hasSubmitted("Stud1", "hw1", "Test", 2017));
    	
    } //true case
    
    @Test
    public void submitHomeworkNoHomework() {
    	this.admin.createClass("Test", 2017, "Teacher", 15);
    	//this.instructor.addHomework("Teacher", "Test", 2017, "hw1");
    	this.student.registerForClass("Stud1", "Test", 2017);
    	this.student.submitHomework("Stud1", "hw1", "Answer", "Test", 2017);
    	assertFalse(this.student.hasSubmitted("Stud1", "hw1", "Test", 2017));
    	
    } //No homework was created
    
    @Test
    public void submitHomeworkNotRegistered() {
    	this.admin.createClass("Test", 2017, "Teacher", 15);
    	this.instructor.addHomework("Teacher", "Test", 2017, "hw1");
    	//this.student.registerForClass("Stud1", "Test", 2017);
    	this.student.submitHomework("Stud1", "hw1", "Answer", "Test", 2017);
    	assertFalse(this.student.hasSubmitted("Stud1", "hw1", "Test", 2017));
    	
    } //Student is not registered
    
    @Test
    public void submitHomeworkFuture() {
    	this.admin.createClass("Test", 2018, "Teacher", 15);
    	this.instructor.addHomework("Teacher", "Test", 2018, "hw1");
    	this.student.registerForClass("Stud1", "Test", 2018);
    	this.student.submitHomework("Stud1", "hw1", "Answer", "Test", 2018);
    	assertFalse(this.student.hasSubmitted("Stud1", "hw1", "Test", 2018));
    	
    } //Can't submit homework for classes that are in future
    
    @Test
    public void submitHomeworkEmptyAnswer() {
    	this.admin.createClass("Test", 2017, "Teacher", 15);
    	this.instructor.addHomework("Teacher", "Test", 2017, "hw1");
    	this.student.registerForClass("Stud1", "Test", 2017);
    	this.student.submitHomework("Stud1", "hw1", "", "Test", 2017);
    	assertFalse(this.student.hasSubmitted("Stud1", "hw1", "Test", 2017));
    	
    } //Student submitted file with no name -> invalid case
    
     
    

}
