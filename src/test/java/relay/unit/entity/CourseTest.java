package relay.unit.entity;

import org.junit.jupiter.api.Test;
import relay.entity.Course;

import java.sql.Timestamp;

public class CourseTest {

	/**
	 * Test case for verifying the getter method for Course ID.
	 */
	@Test
	public void testGetCourseID() {
		Course course = new Course("CSC 110", "ztz");
		course.setCourseID("zyx");
		assert course.getCourseID().equals("zyx");
	}

	/**
	 * Test case for verifying the getter method for Course Name.
	 */
	@Test
	public void testGetCourseName() {
		Course course = new Course("CSC 110", "ztz");
		assert course.getCourseName().equals("CSC 110");
	}

	/**
	 * Test case for verifying the getter method for Instructor ID.
	 */
	@Test
	public void testGetInstructorID() {
		Course course = new Course("CSC 110", "ztz");
		assert course.getInstructorID().equals("ztz");
	}

	/**
	 * Test case for verifying the getter method for Course History.
	 */
	@Test
	public void testGetHistory() {
		Course course = new Course("CSC 110", "ztz");
		assert course.getHistory().isEmpty();
	}

	/**
	 * Test case for verifying the setter method for Course ID.
	 */
	@Test
	public void testSetCourseID() {
		Course course = new Course("CSC 110", "ztz");
		course.setCourseID("CSC 207");
		assert course.getCourseID().equals("CSC 207");
	}

	/**
	 * Test case for verifying the setter method for Course Name.
	 */
	@Test
	public void testSetCourseName() {
		Course course = new Course("CSC 110", "ztz");
		course.setCourseName("Clean Architecture");
		assert course.getCourseName().equals("Clean Architecture");
	}

	/**
	 * Test case for verifying the setter method for Instructor ID.
	 */
	@Test
	public void testSetInstructorID() {
		Course course = new Course("CSC 110", "ztz");
		course.setInstructorID("zzz");
		assert course.getInstructorID().equals("zzz");
	}
}
