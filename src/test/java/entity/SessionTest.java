package entity;

import org.junit.jupiter.api.Test;
import relay.entity.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SessionTest {

    @Test
    public void testGetSetAttendance() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AttendanceRecord record = AttendanceRecordFactory.createAttendanceRecord("John", "Doe", "12345",
                "John@gmail.com");
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);

        Session session = new Session(attendanceRecords, "SAMPLE_COURSE_ID", "SAMPLE_INSTRUCTOR_ID", timestamp);
        List<AttendanceRecord> newAttendanceRecords = new ArrayList<>();
        session.setAttendance(newAttendanceRecords);
        assert session.getAttendance().equals(newAttendanceRecords);
    }

    @Test
    public void testGetSetCourseID() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AttendanceRecord record = AttendanceRecordFactory.createAttendanceRecord("John", "Doe", "12345",
                "John@gmail.com");
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);

        Session session = new Session(attendanceRecords, "SAMPLE_COURSE_ID", "SAMPLE_INSTRUCTOR_ID", timestamp);
        session.setCourseID("NEW_COURSE_ID");
        assert session.getCourseID().equals("NEW_COURSE_ID");
    }

    @Test
    public void testGetSetInstructorID() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AttendanceRecord record = AttendanceRecordFactory.createAttendanceRecord("John", "Doe", "12345",
                "John@gmail.com");
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);

        Session session = new Session(attendanceRecords, "SAMPLE_COURSE_ID", "SAMPLE_INSTRUCTOR_ID", timestamp);
        session.setCourseID("NEW_COURSE_ID");
        session.setInstructorID("NEW_INSTRUCTOR_ID");
        assert session.getInstructorID().equals("NEW_INSTRUCTOR_ID");
    }

    @Test
    public void testGetSetStartedAt() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AttendanceRecord record = AttendanceRecordFactory.createAttendanceRecord("John", "Doe", "12345",
                "John@gmail.com");
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);

        Session session = new Session(attendanceRecords, "SAMPLE_COURSE_ID", "SAMPLE_INSTRUCTOR_ID", timestamp);
        Timestamp newTimestamp = new Timestamp(System.nanoTime());
        session.setStartedAt(newTimestamp);
        assert session.getStartedAt().equals(newTimestamp);
    }
}