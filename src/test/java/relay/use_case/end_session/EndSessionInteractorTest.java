package relay.use_case.end_session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import relay.InMemoryCourseDataAccessObject;
import relay.InMemorySessionDataAccessObject;
import relay.entity.AttendanceRecord;
import relay.entity.Course;
import relay.entity.Session;
import relay.exceptions.ResourceAlreadyExistsException;
import relay.exceptions.ResourceNotFoundException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the {@link EndSessionInteractor} class.
 */
public class EndSessionInteractorTest {

    /**
     * Interface for accessing instructor login data during testing.
     */
    EndSessionSessionDataAccessInterface sessionRepository;
    EndSessionCourseDataAccessInterface courseRepository;

    /**
     * Set up the instructor repository before each test.
     */
    @BeforeEach
    void setupSessionRepository() {
        this.sessionRepository = new InMemorySessionDataAccessObject();
        this.courseRepository = new InMemoryCourseDataAccessObject();
    }

    /**
     * Test for successful end session scenario, when the response data passed in is correct.
     */
    @Test
    void successTest() throws ResourceAlreadyExistsException, ResourceNotFoundException {
        Course course = new Course("MAT237", "Asif Zaman");
        course.setCourseID("MAT237");
        ((InMemoryCourseDataAccessObject) courseRepository).save(course);
        Session session = new Session(new ArrayList<AttendanceRecord>(), "MAT237", "Asif Zaman", Timestamp.from(Instant.now()));
        session.setSessionID("12");
        sessionRepository.save(session);
        EndSessionInputData loginInputData = new EndSessionInputData("12");

        EndSessionOutputBoundary successPresenter = new EndSessionOutputBoundary() {
            @Override
            public ResponseEntity<HttpStatus> prepareSuccessResponse(EndSessionOutputData endSessionOutputData) {
                assertTrue(endSessionOutputData.getUseCaseSuccess());
                assert endSessionOutputData.getErrorMessage() == null;
                return null;
            }

            @Override
            public ResponseEntity<HttpStatus> prepareFailResponse(EndSessionOutputData endSessionOutputData) {
                throw new RuntimeException("Use Case Failure Not Expected");
            }
        };

        EndSessionInteractor interactor = new EndSessionInteractor(sessionRepository, courseRepository, successPresenter);
        interactor.execute(loginInputData);
    }

    /**
     * Test for failure when a session ends twice.
     */
    @Test
    void failureSameSessionTwice() throws ResourceNotFoundException {
        Course course = new Course("MAT237", "Asif Zaman");
        course.setCourseID("MAT237");
        ((InMemoryCourseDataAccessObject) courseRepository).save(course);
        Session session = new Session(new ArrayList<AttendanceRecord>(), "MAT237", "Asif Zaman", Timestamp.from(Instant.now()));
        session.setSessionID("12");
        sessionRepository.save(session);
        EndSessionInputData loginInputData = new EndSessionInputData("12");

        EndSessionOutputBoundary dummyPresenter = new EndSessionOutputBoundary() {
            @Override
            public ResponseEntity<HttpStatus> prepareSuccessResponse(EndSessionOutputData endSessionOutputData) {
                throw new RuntimeException("Use Case Success Not Expected");
            }

            @Override
            public ResponseEntity<HttpStatus> prepareFailResponse(EndSessionOutputData endSessionOutputData) {
                assertFalse(endSessionOutputData.getErrorMessage().isBlank());
                assertFalse(endSessionOutputData.getUseCaseSuccess());
                return null;
            }
        };

        EndSessionInteractor interactor1 = new EndSessionInteractor(sessionRepository, courseRepository, dummyPresenter);
        interactor1.execute(loginInputData);
        EndSessionOutputBoundary failurePresenter = new EndSessionOutputBoundary() {
            @Override
            public ResponseEntity<HttpStatus> prepareSuccessResponse(EndSessionOutputData endSessionOutputData) {
                return null;
            }

            @Override
            public ResponseEntity<HttpStatus> prepareFailResponse(EndSessionOutputData endSessionOutputData) {
                throw new RuntimeException("Use Case Failure Not Expected");
            }
        };


        EndSessionInteractor interactor2 = new EndSessionInteractor(sessionRepository, courseRepository, failurePresenter);

    }
}
