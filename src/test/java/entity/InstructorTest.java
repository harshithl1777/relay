package entity;

import org.junit.jupiter.api.Test;
import relay.entity.Instructor;

public class InstructorTest {
    /**
     * Test to verify the retrieval of the instructor's first name.
     */
    @Test
    public void getInstructorFirstName(){
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        assert instructor.getFirstName().equals("Jane");
    }

    /**
     * Test to verify the retrieval of the instructor's last name.
     */
    @Test
    public void getInstructorLastName() {
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        assert instructor.getLastName().equals("Doe");
    }

    /**
     * Test to verify the retrieval of the instructor's email address.
     */
    @Test
    public void getInstructorEmailAddress() {
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        assert instructor.getEmailAddress().equals("jane@gmail.com");
    }

    /**
     * Test to verify the setting of a new first name for the instructor.
     */
    @Test
    public void setInstructorFirstName() {
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        instructor.setFirstName("John");
        assert instructor.getFirstName().equals("John");
    }

    /**
     * Test to verify the setting of a new last name for the instructor.
     */
    @Test
    public void setInstructorLastName() {
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        instructor.setLastName("Smith");
        assert instructor.getLastName().equals("Smith");
    }

    /**
     * Test to verify the setting of a new email address for the instructor.
     */
    @Test
    public void setInstructorEmailAddress() {
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        instructor.setEmailAddress("jane123@gmail.com");
        assert instructor.getEmailAddress().equals("jane123@gmail.com");
    }
}