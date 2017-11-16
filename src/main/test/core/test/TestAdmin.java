package core.test;

import core.api.IAdmin;
import core.api.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import core.api.IStudent;
import core.api.impl.Student;

/**
 * Created by Vincent on 23/2/2017.
 */
public class TestAdmin {

    private IAdmin admin;
    private IStudent student;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
    }

    @Test
    public void testMakeClass() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    } //regular class creation - valid

    @Test
    public void testMakeClass2() {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }//making a class in the past - invalid
    
    @Test
    public void instructorCourseLimitViolated() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.createClass("Test2", 2017, "Instructor", 15);
        this.admin.createClass("Test3", 2017, "Instructor", 15);
        assertFalse(this.admin.classExists("Test3", 2017));
    }// instructor cannot teach more than 2 classes
    
    @Test
    public void instructorCourseLimitCourseCase() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.createClass("Test2", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test2", 2017));
    } //instructor teaches 2 classes
    
    @Test
    public void instructorNameInvalid() {
        this.admin.createClass("Test", 2017, "", 15);
        assertFalse(this.admin.classExists("Test", 2017));
    } //No instructor name ------> Ask the TA
    
    @Test
    public void classNameInvalid() {
        this.admin.createClass("", 2017, "Instructor", 15);
        assertFalse(this.admin.classExists("", 2017));
    } // class name is empty
    
    @Test
    public void capacity0() {
        this.admin.createClass("Test", 2017, "Instructor", 0);
        assertFalse(this.admin.classExists("Test", 2017));
    } //Zero capacity - Has to be grater than 0
    
    @Test
    public void capacityNegative() {
        this.admin.createClass("Test", 2017, "Instructor", -1);
        assertFalse(this.admin.classExists("Test", 2017));
    } //Negative capacity - Invalid
    
    @Test
    public void classDuplicate() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.createClass("Test", 2017, "Instructor2", 15);
        assertFalse((this.admin.getClassInstructor("Test", 2017)).equals("Instructor2"));
        
    } //Class being taught by 2 instructors - Invalid
    
    @Test
    public void changeCapacityTest1() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("Student", "Test", 2017);
        this.admin.changeCapacity("Test", 2017, 16 );
        assertEquals(16, this.admin.getClassCapacity("Test", 2017));
                
    } //Changing class more than the enrolled - true case
    
    @Test
    public void changeCapacityTest2() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("Student", "Test", 2017);
        this.student.registerForClass("Student2", "Test", 2017);
        this.student.registerForClass("Student3", "Test", 2017);
        this.admin.changeCapacity("Test", 2017, 1);
        assertEquals(15, this.admin.getClassCapacity("Test", 2017));
        
    } //Changing class less than the enrolled - false case
    
    @Test
    public void changeCapacityTest3() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test", 2017, 1);
        assertEquals(1, this.admin.getClassCapacity("Test", 2017));
    } //Changing class to less than prev capacity  but equal to enrolled - true case -> corner case
    
    @Test
    public void changeCapacityTest4() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test", 2017, 0);
        assertEquals(15, this.admin.getClassCapacity("Test", 2017));
    } //Changing class to capacity 0 - false case
    
    @Test
    public void changeCapacityTest5() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test", 2017, -1);
        assertEquals(15, this.admin.getClassCapacity("Test", 2017));
        
    } //Changing class to negative capacity - false case
    
    @Test
    public void changeCapacityTest6() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test", 2017, 16);
        assertEquals(16, this.admin.getClassCapacity("Test", 2017));
        
    } //Changing class more than the enrolled - false case
    
}

