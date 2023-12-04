package relay.unit.entity;

import org.junit.jupiter.api.Test;
import relay.entity.Instructor;

public class InstructorTest {

	/**
	 * Test case to verify the getter method for Instructor's first name.
	 */
	@Test
	public void getInstructorFirstName() {
		Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
		assert instructor.getFirstName().equals("Jane");
	}

	/**
	 * Test case to verify the getter method for Instructor's last name.
	 */
	@Test
	public void getInstructorLastName() {
		Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
		assert instructor.getLastName().equals("Doe");
	}

	/**
	 * Test case to verify the getter method for Instructor's email address.
	 */
	@Test
	public void getInstructorEmailAddress() {
		Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
		assert instructor.getEmailAddress().equals("jane@gmail.com");
	}

	/**
	 * Test case to verify the setter method for Instructor's first name.
	 */
	@Test
	public void setInstructorFirstName() {
		Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
		instructor.setFirstName("John");
		assert instructor.getFirstName().equals("John");
	}

	/**
	 * Test case to verify the setter method for Instructor's last name.
	 */
	@Test
	public void setInstructorLastName() {
		Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
		instructor.setLastName("Smith");
		assert instructor.getLastName().equals("Smith");
	}

	/**
	 * Test case to verify the setter method for Instructor's email address.
	 */
	@Test
	public void setInstructorEmailAddress() {
		Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
		instructor.setEmailAddress("jane123@gmail.com");
		assert instructor.getEmailAddress().equals("jane123@gmail.com");
	}
}