package data_access;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import relay.data_access.FirebaseCourseDataAccessObject;
import relay.data_access.FirebaseInitializationSingleton;
import relay.data_access.FirebaseInstructorDataAccessObject;
import relay.entity.Course;
import relay.entity.Instructor;
import relay.exceptions.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.*;



public class FirebaseCourseDataAccessObjectTest {
    FirebaseCourseDataAccessObject courseDataAccessObject;
    FirebaseInstructorDataAccessObject instructorDataAccessObject;

    @BeforeAll
    public static void initializeFirebase() {
        FirebaseInitializationSingleton.initialize();
    }
    @BeforeEach
    void initializeDataAccessObject(){
        this.courseDataAccessObject = new FirebaseCourseDataAccessObject();
        this.instructorDataAccessObject = new FirebaseInstructorDataAccessObject();
    }

    /**
     * Tests the creation and retrieval of a course.
     */
    @Test
    void createAndReadCourse(){
        Instructor instructor = new Instructor("first", "last","first.last@gmail.com");
        instructorDataAccessObject.save(instructor);
        Course course = new Course("STA157", instructor);
        courseDataAccessObject.save(course);
        Course retrievedCourse = courseDataAccessObject.getCoursesByInstructor(instructor.getInstructorID()).get(0);

        assertNotNull(retrievedCourse);
        assertEquals(retrievedCourse.getCourseID(), course.getCourseID());
        assertEquals(retrievedCourse.getCourseName(), course.getCourseName());
        assertEquals(retrievedCourse.getInstructor().getInstructorID(), course.getInstructor().getInstructorID());

        courseDataAccessObject.delete(course.getCourseID());
        instructorDataAccessObject.delete(instructor.getInstructorID());

    }

    /**
     * Tests the existence of an already created course.
     */
    @Test
    void testExistentCourseExists(){
        Instructor instructor = new Instructor("first", "last","first.last@gmail.com");
        Course course = new Course("STA157", instructor);
        courseDataAccessObject.save(course);

        assertTrue(courseDataAccessObject.exists(course.getCourseID()));

        courseDataAccessObject.delete(course.getCourseID());
    }

    /**
     * Tests the existence of a non-existent course.
     */
    @Test
    void testNonExistentCourseExists(){
        assertFalse(courseDataAccessObject.exists("dummyCourseId"));
    }

    /**
     * Tests the deletion of an existent course and verifies its non-existence after deletion.
     */
    @Test
    void testDeleteExistentCourse(){
        Instructor instructor = new Instructor("first", "last","first.last@gmail.com");
        Course course = new Course("STA157", instructor);
        courseDataAccessObject.save(course);

        courseDataAccessObject.delete(course.getCourseID());

        assertFalse(courseDataAccessObject.exists(course.getCourseID()));

    }

    /**
     * Tests the deletion of a non-existent course, expecting a ResourceNotFoundException to be thrown.
     */
    @Test
    void testDeleteNonExistentCourse(){
        assertThrows(ResourceNotFoundException.class, () -> courseDataAccessObject.delete("dummyCourseId"));
    }

}
