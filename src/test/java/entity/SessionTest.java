package entity;

import org.junit.jupiter.api.Test;
import relay.entity.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SessionTest {
    @Test
    public void testGetCourse() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Student student = new Student("John", "Doe", "12345", "John@gmail.com");
        AttendanceRecord record = new AttendanceRecord(student, timestamp);
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        Course course = new Course("CSC 110", "Software Engineering", instructor);

        Session session = new Session(attendanceRecords, course, instructor, timestamp);
        assert session.getCourse().equals(course);
    }
    @Test
    public void testGetInstructor() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Student student = new Student("John", "Doe", "12345", "John@gmail.com");
        AttendanceRecord record = new AttendanceRecord(student, timestamp);
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        Course course = new Course("CSC 110", "Software Engineering", instructor);

        Session session = new Session(attendanceRecords, course, instructor, timestamp);
        assert session.getInstructor().equals(instructor);
    }
    @Test
    public void testGetStartedAt() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Student student = new Student("John", "Doe", "12345", "John@gmail.com");
        AttendanceRecord record = new AttendanceRecord(student, timestamp);
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        Course course = new Course("CSC 110", "Software Engineering", instructor);

        Session session = new Session(attendanceRecords, course, instructor, timestamp);
        assert session.getStartedAt().equals(timestamp);
    }
    @Test
    public void testSetAttendance() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Student student = new Student("John", "Doe", "12345", "John@gmail.com");
        AttendanceRecord record = new AttendanceRecord(student, timestamp);
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        Course course = new Course("CSC 110", "Software Engineering", instructor);

        Session session = new Session(attendanceRecords, course, instructor, timestamp);
        List<AttendanceRecord> newAttendanceRecords = new ArrayList<>();
        session.setAttendance(newAttendanceRecords);
        assert session.getAttendance().equals(newAttendanceRecords);
    }
    @Test
    public void testSetCourse() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Student student = new Student("John", "Doe", "12345", "John@gmail.com");
        AttendanceRecord record = new AttendanceRecord(student, timestamp);
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        Course course = new Course("CSC 110", "Software Engineering", instructor);

        Session session = new Session(attendanceRecords, course, instructor, timestamp);
        Course newCourse = new Course("CSC 207", "Software Engineering", instructor);
        session.setCourse(newCourse);
        assert session.getCourse().equals(newCourse);
    }
    @Test
    public void testSetInstructor() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Student student = new Student("John", "Doe", "12345", "John@gmail.com");
        AttendanceRecord record = new AttendanceRecord(student, timestamp);
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        Course course = new Course("CSC 110", "Software Engineering", instructor);

        Session session = new Session(attendanceRecords, course, instructor, timestamp);
        Instructor newInstructor = new Instructor("John", "Doe", "john@gmail.com");
        session.setInstructor(newInstructor);
        assert session.getInstructor().equals(newInstructor);
    }
    @Test
    public void testSetStartedAt() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Student student = new Student("John", "Doe", "12345", "John@gmail.com");
        AttendanceRecord record = new AttendanceRecord(student, timestamp);
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(record);
        Instructor instructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        Course course = new Course("CSC 110", "Software Engineering", instructor);

        Session session = new Session(attendanceRecords, course, instructor, timestamp);
        Timestamp newTimestamp = new Timestamp(System.nanoTime());
        session.setStartedAt(newTimestamp);
        assert session.getStartedAt().equals(newTimestamp);
    }
}
