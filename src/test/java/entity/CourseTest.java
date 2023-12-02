package entity;

import org.junit.jupiter.api.Test;
import relay.entity.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CourseTest {
    /**
     * Test to verify the retrieval of the course ID.
     */
    @Test
    public void testGetCourseID() {
        Course course = new Course("CSC 110", "Software Engineering", new Instructor("John", "Doe", "john@gmail.com"));
        assert course.getCourseID().equals("CSC 110");
    }

    /**
     * Test to verify the retrieval of the course name.
     */
    @Test
    public void testGetCourseName() {
        Course course = new Course("CSC 110", "Software Engineering", new Instructor("John", "Doe", "john@gmail.com"));
        assert course.getCourseName().equals("Software Engineering");
    }

    /**
     * Test to verify the retrieval of the instructor associated with the course.
     */
    @Test
    public void testGetInstructor() {
        Instructor instructor = new Instructor("John", "Doe", "john@gmail.com");
        Course course = new Course("CSC 110", "Software Engineering", instructor);
        assert course.getInstructor().equals(instructor);
    }

    /**
     * Test to verify that the course history is initially empty.
     */
    @Test
    public void testGetHistory() {
        Course course = new Course("CSC 110", "Software Engineering", new Instructor("John", "Doe", "john@gmail.com"));
        assert course.getHistory().isEmpty();
    }

    /**
     * Test to verify the setting of the course ID.
     */
    @Test
    public void testSetCourseID() {
        Course course = new Course("CSC 110", "Software Engineering", new Instructor("John", "Doe", "john@gmail.com"));
        course.setCourseID("CSC 207");
        assert course.getCourseID().equals("CSC 207");
    }

    /**
     * Test to verify the setting of the course name.
     */
    @Test
    public void testSetCourseName() {
        Course course = new Course("CSC 110", "Software Engineering", new Instructor("John", "Doe", "john@gmail.com"));
        course.setCourseName("Clean Architecture");
        assert course.getCourseName().equals("Clean Architecture");
    }

    /**
     * Test to verify the setting of a new instructor for the course.
     */
    @Test
    public void testSetInstructor() {
        Instructor instructor = new Instructor("John", "Doe", "john@gmail.com");
        Course course = new Course("CSC 110", "Software Engineering", instructor);
        Instructor newInstructor = new Instructor("Jane", "Doe", "jane@gmail.com");
        course.setInstructor(newInstructor);
        assert course.getInstructor().equals(newInstructor);
    }

    /**
     * Test to verify the setting of the course history with attendance records.
     */
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