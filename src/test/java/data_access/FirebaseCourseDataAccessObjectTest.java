package data_access;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import relay.data_access.FirebaseCourseDataAccessObject;
import relay.data_access.FirebaseInitializationSingleton;
import relay.entity.Course;
import relay.entity.Instructor;

import static org.junit.jupiter.api.Assertions.*;



public class FirebaseCourseDataAccessObjectTest {
    FirebaseCourseDataAccessObject dataAccessObject;

    @BeforeAll
    public static void initializeFirebase() {
        FirebaseInitializationSingleton.initialize();
    }
    @BeforeEach
    void initializeDataAccessObject(){
        this.dataAccessObject = new FirebaseCourseDataAccessObject();
    }

    /**
     * Tests the creation and retrieval of a course.
     */
    @Test
    void createAndReadCourse(){
        Instructor instructor = new Instructor("first", "last","first.last@gmail.com");
        Course course = new Course("STA157", instructor);
        dataAccessObject.saveCourse(course);
        Course retrievedCourse = dataAccessObject.getCoursesByInstructor(instructor.getInstructorID()).get(0);

        assertNotNull(retrievedCourse);
        assertEquals(retrievedCourse.getCourseID(), course.getCourseID());
        assertEquals(retrievedCourse.getCourseName(), course.getCourseName());
        assertEquals(retrievedCourse.getInstructor().getInstructorID(), course.getInstructor().getInstructorID());

        dataAccessObject.delete(course.getCourseID());

    }

    /**
     * Tests the existence of an already created course.
     */
    @Test
    void testExistentCourseExists(){
        Instructor instructor = new Instructor("first", "last","first.last@gmail.com");
        Course course = new Course("STA157", instructor);
        dataAccessObject.saveCourse(course);

        assertTrue(dataAccessObject.exists(course.getCourseID()));

        dataAccessObject.delete(course.getCourseID());
    }

    /**
     * Tests the existence of a non-existent course.
     */
    @Test
    void testNonExistentCourseExists(){
        assertFalse(dataAccessObject.exists("dummyCourseId"));
    }

    /**
     * Tests the deletion of an existent course and verifies its non-existence after deletion.
     */
    @Test
    void testDeleteExistentCourse(){
        Instructor instructor = new Instructor("first", "last","first.last@gmail.com");
        Course course = new Course("STA157", instructor);
        dataAccessObject.saveCourse(course);

        dataAccessObject.delete(course.getCourseID());

        assertFalse(dataAccessObject.exists(course.getCourseID()));

    }

    /**
     * Tests the deletion of a non-existent course, expecting a ResourceNotFoundException to be thrown.
     */
    @Test
    void testDeleteNonExistentCourse(){
        assertThrows(ResourceNotFoundException.class, () -> dataAccessObject.delete("dummyCourseId"));
    }

}
