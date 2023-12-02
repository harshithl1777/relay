package entity;

import org.junit.jupiter.api.Test;
import relay.entity.Instructor;

public class InstructorTest {
    @Test
    public void getInstructorFirstName(){
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        assert instructor.getFirstName().equals("Jane");
    }
    @Test
    public void getInstructorLastName() {
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        assert instructor.getLastName().equals("Doe");
    }
    @Test
    public void getInstructorEmailAddress() {
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        assert instructor.getEmailAddress().equals("jane@gmail.com");
    }
    @Test
    public void setInstructorFirstName() {
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        instructor.setFirstName("John");
        assert instructor.getFirstName().equals("John");
    }
    @Test
    public void setInstructorLastName() {
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        instructor.setLastName("Smith");
        assert instructor.getLastName().equals("Smith");
    }
    @Test
    public void setInstructorEmailAddress() {
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        instructor.setEmailAddress("jane123@gmail.com");
        assert instructor.getEmailAddress().equals("jane123@gmail.com");
    }
}
