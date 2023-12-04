package relay.use_case.start_session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import relay.InMemoryCourseDataAccessObject;
import relay.InMemoryInstructorDataAccessObject;
import relay.InMemorySessionDataAccessObject;
import relay.entity.Course;
import relay.entity.Instructor;
import relay.entity.SessionFactory;
import relay.exceptions.ResourceAlreadyExistsException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the {@link StartSessionInteractor} class.
 */
public class StartSessionInteractorTest {

    /**
     * Interface for accessing instructor login data during testing.
     */
    StartSessionSessionDataAccessInterface sessionRepository;
    StartSessionCourseDataAccessInterface courseRepository;
    StartSessionInstructorDataAccessInterface instructorRepository;

    /**
     * Set up the instructor repository before each test.
     */
    @BeforeEach
    void setupSessionRepository() {
        this.sessionRepository = new InMemorySessionDataAccessObject();
        this.courseRepository = new InMemoryCourseDataAccessObject();
        this.instructorRepository = new InMemoryInstructorDataAccessObject();
    }

    /**
     * Test for successful start session scenario, when the response data passed in is correct.
     */
    @Test
    void successTest() throws ResourceAlreadyExistsException {
        Course course = new Course("MAT237", "Asif Zaman");
        course.setCourseID("MAT237");
        ((InMemoryCourseDataAccessObject) courseRepository).save(course);
        Instructor instructor = new Instructor("Asif", "Zaman", "asif@zaman@gmail.com");
        instructor.setInstructorID("Asif Zaman");
        ((InMemoryInstructorDataAccessObject) instructorRepository).save(instructor);
        StartSessionInputData loginInputData = new StartSessionInputData("MAT237", "Asif Zaman");

        StartSessionOutputBoundary successPresenter = new StartSessionOutputBoundary() {
            @Override
            public ResponseEntity<Map<String, Object>> prepareSuccessResponse(StartSessionOutputData startSessionOutputData) {
                assertTrue(startSessionOutputData.getUseCaseSuccess());
                assert startSessionOutputData.getErrorMessage() == null;
                assert startSessionOutputData.getSessionID() != null;
                assertEquals("MAT237", startSessionOutputData.getCourseID());
                assertEquals("Asif Zaman", startSessionOutputData.getInstructorID());
                assert startSessionOutputData.getSessionCode() != null;
                assert startSessionOutputData.getAttendance() != null;
                assert startSessionOutputData.getAttendance().isEmpty();
                return null;
            }

            @Override
            public ResponseEntity<Map<String, Object>> prepareFailResponse(StartSessionOutputData startSessionOutputData) {
                throw new RuntimeException("Use Case Failure Not Expected");
            }
        };

        StartSessionInteractor interactor = new StartSessionInteractor(sessionRepository, courseRepository, instructorRepository, successPresenter, new SessionFactory());
        interactor.execute(loginInputData);
    }

    /**
     * Test for failure when the same session is created twice, without the previous one ending.
     */
    @Test
    void failureSameSessionTwice() {
        StartSessionInputData startSessionInputData = new StartSessionInputData("MAT237", "Asif Zaman");
        StartSessionOutputBoundary dummyPresenter = new StartSessionOutputBoundary() {
            @Override
            public ResponseEntity<Map<String, Object>> prepareSuccessResponse(StartSessionOutputData startSessionOutputData) {
                return null;
            }

            @Override
            public ResponseEntity<Map<String, Object>> prepareFailResponse(StartSessionOutputData failurestartSessionOutputData) {
                return null;
            }
        };

        StartSessionInteractor interactor1 = new StartSessionInteractor(sessionRepository, courseRepository, instructorRepository, dummyPresenter, new SessionFactory());
        interactor1.execute(startSessionInputData);

        StartSessionOutputBoundary failurePresenter = new StartSessionOutputBoundary() {
            @Override
            public ResponseEntity<Map<String, Object>> prepareSuccessResponse(StartSessionOutputData startSessionOutputData) {
                throw new RuntimeException("Use Case Success Not Expected");
            }

            @Override
            public ResponseEntity<Map<String, Object>> prepareFailResponse(StartSessionOutputData failurestartSessionOutputData) {
                assert !failurestartSessionOutputData.getErrorMessage().isBlank();  // test if error message has been set
                return null;
            }
        };

        StartSessionInteractor interactor2 = new StartSessionInteractor(sessionRepository, courseRepository, instructorRepository, failurePresenter, new SessionFactory());
        interactor2.execute(startSessionInputData);
    }
}
