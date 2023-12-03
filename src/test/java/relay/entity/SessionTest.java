package relay.entity;

import org.junit.jupiter.api.Test;
import relay.entity.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SessionTest {

	/**
	 * Test to verify the retrieval of the Course ID from a Session.
	 * Creates a session, sets up attendance records, course, and instructor IDs,
	 * then checks if the retrieved course ID matches the expected course ID.
	 */
	@Test
	public void testGetCourse() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AttendanceRecord record = new AttendanceRecord("John", "Doe", "12345", "John@gmail.com", timestamp);
		List<AttendanceRecord> attendanceRecords = new ArrayList<>();
		attendanceRecords.add(record);
		String instructorID = "zyz";
		String courseID = "zxz";

		Session session = new Session(attendanceRecords, courseID, instructorID, timestamp);
		assert session.getCourseID().equals(courseID);
	}

	/**
	 * Test to verify the retrieval of the Instructor ID from a Session.
	 */
	@Test
	public void testGetInstructor() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AttendanceRecord record = new AttendanceRecord("John", "Doe", "12345", "John@gmail.com", timestamp);
		List<AttendanceRecord> attendanceRecords = new ArrayList<>();
		attendanceRecords.add(record);
		String instructorID = "zyz";
		String courseID = "zxz";

		Session session = new Session(attendanceRecords, courseID, instructorID, timestamp);
		assert session.getInstructorID().equals(instructorID);
	}

	/**
	 * Test to verify the retrieval of the start time from a Session.
	 */
	@Test
	public void testGetStartedAt() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AttendanceRecord record = new AttendanceRecord("John", "Doe", "12345", "John@gmail.com", timestamp);
		List<AttendanceRecord> attendanceRecords = new ArrayList<>();
		attendanceRecords.add(record);
		String instructorID = "zyz";
		String courseID = "zxz";

		Session session = new Session(attendanceRecords, courseID, instructorID, timestamp);
		assert session.getStartedAt().equals(timestamp);
	}

	/**
	 * Test to verify the setting of the attendance records in a Session.
	 */
	@Test
	public void testSetAttendance() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AttendanceRecord record = new AttendanceRecord("John", "Doe", "12345", "John@gmail.com", timestamp);
		List<AttendanceRecord> attendanceRecords = new ArrayList<>();
		attendanceRecords.add(record);
		String instructorID = "zyz";
		String courseID = "zxz";

		Session session = new Session(attendanceRecords, courseID, instructorID, timestamp);
		List<AttendanceRecord> newAttendanceRecords = new ArrayList<>();
		session.setAttendance(newAttendanceRecords);
		assert session.getAttendance().equals(newAttendanceRecords);
	}

	/**
	 * Test to verify the setting of the Course ID in a Session.
	 */
	@Test
	public void testSetCourse() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AttendanceRecord record = new AttendanceRecord("John", "Doe", "12345", "John@gmail.com", timestamp);
		List<AttendanceRecord> attendanceRecords = new ArrayList<>();
		attendanceRecords.add(record);
		String instructorID = "zyz";
		String courseID = "zxz";

		Session session = new Session(attendanceRecords, courseID, instructorID, timestamp);
		String newCourseID = "zzy";
		session.setCourseID(newCourseID);
		assert session.getCourseID().equals(newCourseID);
	}

	/**
	 * Test to verify the setting of the Instructor ID in a Session.
	 */
	@Test
	public void testSetInstructor() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AttendanceRecord record = new AttendanceRecord("John", "Doe", "12345", "John@gmail.com", timestamp);
		List<AttendanceRecord> attendanceRecords = new ArrayList<>();
		attendanceRecords.add(record);
		String instructorID = "zyz";
		String courseID = "zxz";

		Session session = new Session(attendanceRecords, courseID, instructorID, timestamp);
		String newInstructorID = "zzy";
		session.setInstructorID(newInstructorID);
		assert session.getInstructorID().equals(newInstructorID);
	}

	/**
	 * Test to verify the setting of the start time in a Session.
	 */
	@Test
	public void testSetStartedAt() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AttendanceRecord record = new AttendanceRecord("John", "Doe", "12345", "John@gmail.com", timestamp);
		List<AttendanceRecord> attendanceRecords = new ArrayList<>();
		attendanceRecords.add(record);
		String instructorID = "zyz";
		String courseID = "zxz";

		Session session = new Session(attendanceRecords, courseID, instructorID, timestamp);
		Timestamp newTimestamp = new Timestamp(System.nanoTime());
		session.setStartedAt(newTimestamp);
		assert session.getStartedAt().equals(newTimestamp);
	}
}
