package relay.integration.signup;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import relay.data_access.FirebaseInitializationSingleton;
import relay.data_access.FirebaseInstructorDataAccessObject;
import relay.exceptions.ResourceNotFoundException;
import relay.unit.InMemoryInstructorDataAccessObject;
import relay.exceptions.ResourceAlreadyExistsException;
import relay.use_case.signup.*;

// TBD

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link SignupInteractor} class.
 * Failure test infeasible here, and will be tested in integration tests.
 */
public class SignupFirebaseIntegrationTest {

    /**
     * Interface for accessing instructor signup data during testing.
     */
    SignupInstructorDataAccessInterface instructorRepository;

    @BeforeAll
    public static void initializeFirebase() {
        FirebaseInitializationSingleton.initialize();
    }


    /**
     * Set up the instructor repository before each test.
     */
    @BeforeEach
    void setupInstructorRepository() {
        this.instructorRepository = new FirebaseInstructorDataAccessObject();
    }

    /**
     * Test for successful signup scenario.
     *
     * @throws ResourceAlreadyExistsException if a resource already exists during the test setup.
     */
    @Test
    void successTest() throws ResourceAlreadyExistsException {
        SignupInputData signupInputData = new SignupInputData("Vihaan", "Chugh", "vihaanchugh@gmail.com");


        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public ResponseEntity<Map<String, Object>> prepareSuccessResponse(SignupOutputData signupOutputData) {
                assertEquals("vihaanchugh@gmail.com", signupOutputData.getEmailAddress());
                assertEquals("Vihaan", signupOutputData.getFirstName());
                assertEquals("Chugh", signupOutputData.getLastName());
                try {
                    assertEquals(((FirebaseInstructorDataAccessObject) instructorRepository).readByEmailAddress("vihaanchugh@gmail.com").getInstructorID(), signupOutputData.getInstructorID());
                } catch (ResourceNotFoundException e) {
                    throw new RuntimeException(e);
                }
                assert signupOutputData.getUseCaseSuccess();
                assertNull(signupOutputData.getErrorMessage());
                // clean database
                try {
                    ((FirebaseInstructorDataAccessObject) instructorRepository).delete(signupOutputData.getInstructorID());
                } catch (ResourceNotFoundException e) {
                    throw new RuntimeException(e);
                }

                return null;
            }

            @Override
            public ResponseEntity<Map<String, Object>> prepareFailResponse(SignupOutputData signupOutputData) {
                throw new RuntimeException("Use Case Failure Not Expected");
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(instructorRepository, successPresenter);
        interactor.execute(signupInputData);
    }


}
