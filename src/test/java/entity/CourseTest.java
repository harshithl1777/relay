package entity;

import org.junit.jupiter.api.Test;
import relay.entity.AttendanceRecord;
import relay.entity.Course;
import relay.entity.Instructor;
import relay.entity.Student;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CourseTest {
    @Test
    public void testGetCourseID() {
        Course course = new Course("CSC 110", "Software Engineering", new Instructor("John", "Doe", "john@gmail.com"));
        assert course.getCourseID().equals("CSC 110");
    }

    @Test
    public void testGetCourseName() {
        Course course = new Course("CSC 110", "Software Engineering", new Instructor("John", "Doe", "john@gmail.com"));
        assert course.getCourseName().equals("Software Engineering");
    }

    @Test
    public void testGetInstructor() {
        Instructor instructor = new Instructor("John", "Doe", "john@gmail.com");
        Course course = new Course("CSC 110", "Software Engineering", instructor);
        assert course.getInstructor().equals(instructor);
    }

    @Test
    public void testGetHistory() {
        Course course = new Course("CSC 110", "Software Engineering", new Instructor("John", "Doe", "john@gmail.com"));
        assert course.getHistory().isEmpty();
    }

    @Test
    public void testSetCourseID() {
        Course course = new Course("CSC 110", "Software Engineering", new Instructor("John", "Doe", "john@gmail.com"));
        course.setCourseID("CSC 207");
        assert course.getCourseID().equals("CSC 207");
    }

    @Test
    public void testSetCourseName() {
        Course course = new Course("CSC 110", "Software Engineering", new Instructor("John", "Doe", "john@gmail.com"));
        course.setCourseName("Clean Architecture");
        assert course.getCourseName().equals("Clean Architecture");
    }

    @Test
    public void testSetInstructor() {
        Instructor instructor = new Instructor("John", "Doe", "john@gmail.com");
        Course course = new Course("CSC 110", "Software Engineering", instructor);
        Instructor newInstructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        course.setInstructor(newInstructor);
        assert course.getInstructor().equals(newInstructor);
    }

    @Test
    public void testSetHistory() {
        Course course = new Course("CSC 110", "Software Engineering", new Instructor("John", "Doe", "john@gmail.com"));
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

        Student student = new Student("John", "Doe", "S12345", "john.doe@example.com");

        AttendanceRecord attendanceRecord = new AttendanceRecord(student, createdAt);
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        attendanceRecords.add(attendanceRecord);

        course.setHistory(attendanceRecords);
        assert course.getHistory().equals(attendanceRecords);
        }
    }
