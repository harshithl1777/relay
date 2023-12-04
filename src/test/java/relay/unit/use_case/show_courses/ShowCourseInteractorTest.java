package relay.unit.use_case.show_courses;

import org.apache.http.protocol.HTTP;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import relay.entity.Course;
import relay.entity.Instructor;
import relay.exceptions.ResourceAlreadyExistsException;
import relay.unit.InMemoryCourseDataAccessObject;
import relay.unit.InMemoryInstructorDataAccessObject;
import relay.unit.InMemorySessionDataAccessObject;
import relay.entity.SessionFactory;
import relay.use_case.show_courses.*;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link ShowCourseInteractor} class.
 */
public class ShowCourseInteractorTest {


    /**
     * Interface for accessing instructor login data during testing.
     */
    ShowCourseCourseDataAccessInterface courseRepository;
    ShowCourseInstructorDataAccessInterface instructorRepository;

    /**
     * Set up the instructor repository before each test.
     */
    @BeforeEach
    void setupSessionRepository() {
        this.courseRepository = new InMemoryCourseDataAccessObject();
        this.instructorRepository = new InMemoryInstructorDataAccessObject();
    }

    /**
     * Test for success when a correct request is made.
     */
    @Test
    void successfulResponseGivenCorrectRequestTest() throws ResourceAlreadyExistsException {
        // replicate user signup and course creation
        Instructor instructor = new Instructor("Asif", "Zaman", "asif.zaman@gmail.com");
        ((InMemoryInstructorDataAccessObject) instructorRepository).save(instructor);
        Course course = new Course("MAT237", instructor.getInstructorID());
        ((InMemoryCourseDataAccessObject) courseRepository).save(course);

        ShowCourseInputData showCoursesInputData = new ShowCourseInputData(instructor.getInstructorID());

        ShowCourseOutputBoundary presenter = new ShowCourseOutputBoundary() {
            @Override
            public ResponseEntity<Map<String, Object>> prepareSuccessResponse(ShowCourseOutputData showCourseOutputData) {
                assert showCourseOutputData.getUseCaseSuccess();
                assertNull(showCourseOutputData.getErrorMessage());
                assertEquals(1, showCourseOutputData.getCourses().size());
                assertEquals(instructor.getInstructorID(), showCourseOutputData.getCourses().get(0).getInstructorID());
                assertEquals("MAT237", showCourseOutputData.getCourses().get(0).getCourseName());
                return null;
            }

            @Override
            public ResponseEntity<Map<String, Object>> prepareFailResponse(ShowCourseOutputData failureshowCourseOutputData) {
                throw new RuntimeException("Use Case failure Not Expected");
            }
        };

        ShowCourseInteractor interactor = new ShowCourseInteractor(courseRepository, instructorRepository, presenter);
        interactor.execute(showCoursesInputData);
    }

    /**
     * Test for success when a correct request is made.
     */
    @Test
    void failedResponseGivenCorrectNonExistentInstructor() throws ResourceAlreadyExistsException {
        ShowCourseInputData showCoursesInputData = new ShowCourseInputData("Non-existent instructorID");
        ShowCourseOutputBoundary presenter = new ShowCourseOutputBoundary() {
            @Override
            public ResponseEntity<Map<String, Object>> prepareSuccessResponse(ShowCourseOutputData showCourseOutputData) {
                throw new RuntimeException("Use Case failure Not Expected");
            }

            @Override
            public ResponseEntity<Map<String, Object>> prepareFailResponse(ShowCourseOutputData failureshowCourseOutputData) {
                assertFalse(failureshowCourseOutputData.getUseCaseSuccess());
                return null;
            }
        };

        ShowCourseInteractor interactor = new ShowCourseInteractor(courseRepository, instructorRepository, presenter);
        interactor.execute(showCoursesInputData);
    }

}