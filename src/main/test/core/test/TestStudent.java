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
    public void registerForClassTestNotRegistered() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("Student2", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("Student", "Test", 2017));
                
    } //Student is not enrolled in class, valid capacity
    
    @Test
    public void registerForClassTestOverCapacity() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test", 2017, 1);
        this.student.registerForClass("Student1", "Test", 2017);
        this.student.registerForClass("Student2", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("Student2", "Test", 2017));
                
    } //2nd student cannot be enrolled in class
    
    @Test
    public void registerForClassTest() {
        this.admin.createClass("Test", 2017, "Instructor", 1);
        //this.admin.changeCapacity("Test", 2017, 1);
        this.student.registerForClass("Student1", "Test", 2017);
        this.student.registerForClass("Student2", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("Student2", "Test", 2017));
                
    } //
    
    
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
                
    } //Student is registered for class and drops it and is valid
   
    /*
    @Test
    public void dropClassTestClassPast() {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        this.student.registerForClass("Student", "Test", 2016);
        this.student.dropClass("Student", "Test", 2016);
        assertFalse(this.student.isRegisteredFor("Student", "Test", 2017));
                
    } //Student cannot drop as class ended in the past
    
    */
    
    
    
    
    
    
    
    

}
