package relay.unit.use_case.start_session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import relay.unit.InMemoryCourseDataAccessObject;
import relay.unit.InMemoryInstructorDataAccessObject;
import relay.unit.InMemorySessionDataAccessObject;
import relay.entity.SessionFactory;
import relay.use_case.start_session.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the {@link StartSessionInteractor} class.
 * Note that only failure is tested as success is infeasible to implement using mock databases.
 * Success will be tested through integration tests.
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