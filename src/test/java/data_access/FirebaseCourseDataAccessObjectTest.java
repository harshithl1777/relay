package data_access;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import relay.data_access.FirebaseCourseDataAccessObject;
import relay.data_access.FirebaseInitializationSingleton;
import relay.entity.Course;
import relay.entity.Instructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class FirebaseCourseDataAccessObjectTest {
    FirebaseCourseDataAccessObject dataAccessObject;

    @BeforeAll
    public static void initializeFirebase() {
        FirebaseInitializationSingleton.initialize();
    }
    @BeforeEach
    void foo(){
        this.dataAccessObject = new FirebaseCourseDataAccessObject();
    }
    @Test
    void createAndReadCourse(){
        Instructor instructor = new Instructor("first", "last","first.last@gmail.com");
        Course course = new Course("", "STA157", instructor);
        dataAccessObject.createCourse(course);
        Course retrievedCourse = dataAccessObject.getCoursesByInstructor(instructor).get(0);

        assertNotNull(retrievedCourse);
        assertEquals(retrievedCourse.getCourseID(), course.getCourseID());
        assertEquals(retrievedCourse.getCourseName(), course.getCourseName());
        assertEquals(retrievedCourse.getInstructor().getInstructorID(), course.getInstructor().getInstructorID());

        dataAccessObject.delete(course.getCourseID());

    }
    @Test
    void testExists(){
        Instructor instructor = new Instructor("first", "last","first.last@gmail.com");
        Course course = new Course("", "STA157", instructor);
        dataAccessObject.createCourse(course);



    }



}
