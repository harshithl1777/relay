package entity;

import org.junit.jupiter.api.Test;
import relay.entity.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SessionTest {

<<<<<<< HEAD
    @Test
    public void testGetSetAttendance() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AttendanceRecord record = AttendanceRecordFactory.createAttendanceRecord("John", "Doe", "12345",
                "John@gmail.com");
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);
=======
	@Test
	public void testGetSetAttendance() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AttendanceRecordFactoryInterface attendanceRecordFactory = new AttendanceRecordFactory();
		AttendanceRecord record = attendanceRecordFactory.createAttendanceRecord("John", "Doe", "12345",
				"John@gmail.com");
		List<AttendanceRecord> attendanceRecords = new ArrayList<>();
		attendanceRecords.add(record);
>>>>>>> 6f54cefff6af308dd933ba3061d225030524439e

        Session session = new Session(attendanceRecords, "SAMPLE_COURSE_ID", "SAMPLE_INSTRUCTOR_ID", timestamp);
        List<AttendanceRecord> newAttendanceRecords = new ArrayList<>();
        session.setAttendance(newAttendanceRecords);
        assert session.getAttendance().equals(newAttendanceRecords);
    }

<<<<<<< HEAD
    @Test
    public void testGetSetCourseID() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AttendanceRecord record = AttendanceRecordFactory.createAttendanceRecord("John", "Doe", "12345",
                "John@gmail.com");
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);
=======
	@Test
	public void testGetSetCourseID() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AttendanceRecordFactoryInterface attendanceRecordFactory = new AttendanceRecordFactory();
		AttendanceRecord record = attendanceRecordFactory.createAttendanceRecord("John", "Doe", "12345",
				"John@gmail.com");
		List<AttendanceRecord> attendanceRecords = new ArrayList<>();
		attendanceRecords.add(record);
>>>>>>> 6f54cefff6af308dd933ba3061d225030524439e

        Session session = new Session(attendanceRecords, "SAMPLE_COURSE_ID", "SAMPLE_INSTRUCTOR_ID", timestamp);
        session.setCourseID("NEW_COURSE_ID");
        assert session.getCourseID().equals("NEW_COURSE_ID");
    }

<<<<<<< HEAD
    @Test
    public void testGetSetInstructorID() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AttendanceRecord record = AttendanceRecordFactory.createAttendanceRecord("John", "Doe", "12345",
                "John@gmail.com");
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);
=======
	@Test
	public void testGetSetInstructorID() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AttendanceRecordFactoryInterface attendanceRecordFactory = new AttendanceRecordFactory();
		AttendanceRecord record = attendanceRecordFactory.createAttendanceRecord("John", "Doe", "12345",
				"John@gmail.com");
		List<AttendanceRecord> attendanceRecords = new ArrayList<>();
		attendanceRecords.add(record);
>>>>>>> 6f54cefff6af308dd933ba3061d225030524439e

        Session session = new Session(attendanceRecords, "SAMPLE_COURSE_ID", "SAMPLE_INSTRUCTOR_ID", timestamp);
        session.setCourseID("NEW_COURSE_ID");
        session.setInstructorID("NEW_INSTRUCTOR_ID");
        assert session.getInstructorID().equals("NEW_INSTRUCTOR_ID");
    }

<<<<<<< HEAD
    @Test
    public void testGetSetStartedAt() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AttendanceRecord record = AttendanceRecordFactory.createAttendanceRecord("John", "Doe", "12345",
                "John@gmail.com");
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);
=======
	@Test
	public void testGetSetStartedAt() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AttendanceRecordFactoryInterface attendanceRecordFactory = new AttendanceRecordFactory();
		AttendanceRecord record = attendanceRecordFactory.createAttendanceRecord("John", "Doe", "12345",
				"John@gmail.com");
		List<AttendanceRecord> attendanceRecords = new ArrayList<>();
		attendanceRecords.add(record);
>>>>>>> 6f54cefff6af308dd933ba3061d225030524439e

        Session session = new Session(attendanceRecords, "SAMPLE_COURSE_ID", "SAMPLE_INSTRUCTOR_ID", timestamp);
        Timestamp newTimestamp = new Timestamp(System.nanoTime());
        session.setStartedAt(newTimestamp);
        assert session.getStartedAt().equals(newTimestamp);
    }
}