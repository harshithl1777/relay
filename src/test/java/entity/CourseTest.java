package entity;

import org.junit.jupiter.api.Test;
import relay.entity.AttendanceRecord;
import relay.entity.Course;
import relay.entity.Student;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CourseTest {
    @Test
    public void testGetCourseID() {
        Course course = new Course("CSC 110",  "ztz");
        course.setCourseID("zyx");
        assert course.getCourseID().equals("zyx");
    }

    @Test
    public void testGetCourseName() {
        Course course = new Course("CSC 110",  "ztz");
        assert course.getCourseName().equals("CSC 110");
    }

    @Test
    public void testGetInstructorID() {
        Course course = new Course("CSC 110",  "ztz");
        assert course.getInstructorID().equals("ztz");
    }

    @Test
    public void testGetHistory() {
        Course course = new Course("CSC 110", "ztz");
        assert course.getHistory().isEmpty();
    }

    @Test
    public void testSetCourseID() {
        Course course = new Course("CSC 110",  "ztz");
        course.setCourseID("CSC 207");
        assert course.getCourseID().equals("CSC 207");
    }

    @Test
    public void testSetCourseName() {
        Course course = new Course("CSC 110",  "ztz");
        course.setCourseName("Clean Architecture");
        assert course.getCourseName().equals("Clean Architecture");
    }

    @Test
    public void testSetInstructorID() {
        Course course = new Course("CSC 110",  "ztz");
        course.setInstructorID("zzz");
        assert course.getInstructorID().equals("zzz");
    }

    @Test
    public void testSetHistory() {
        Course course = new Course("CSC 110",  "zzz");
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

        Student student = new Student("John", "Doe", "S12345", "john.doe@example.com");

        AttendanceRecord attendanceRecord = new AttendanceRecord(student, createdAt);
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(attendanceRecord);

        course.setHistory(attendanceRecords);
        assert course.getHistory().equals(attendanceRecords);
        }
    }
