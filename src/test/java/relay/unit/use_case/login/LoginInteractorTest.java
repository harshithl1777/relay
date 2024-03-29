package relay.unit.use_case.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import relay.unit.InMemoryInstructorDataAccessObject;
import relay.entity.Instructor;
import relay.exceptions.ResourceAlreadyExistsException;
import relay.use_case.login.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test class for the {@link LoginInteractor} class.
 */
public class LoginInteractorTest {

    /**
     * Interface for accessing instructor login data during testing.
     */
    LoginInstructorDataAccessInterface instructorRepository;

    /**
     * Set up the instructor repository before each test.
     */
    @BeforeEach
    void setupInstructorRepository() {
        this.instructorRepository = new InMemoryInstructorDataAccessObject();
    }

    /**
     * Test for successful login scenario.
     *
     * @throws ResourceAlreadyExistsException if a resource already exists during the test setup.
     */
    @Test
    void successTest() throws ResourceAlreadyExistsException {
        LoginInputData loginInputData = new LoginInputData("vihaanchugh@gmail.com");

        // replicate signup
        Instructor instructor = new Instructor("Vihaan", "Chugh", "vihaanchugh@gmail.com");
        instructor.setInstructorID("100879585");
        ((InMemoryInstructorDataAccessObject) instructorRepository).save(instructor);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public ResponseEntity<Map<String, Object>> prepareSuccessResponse(LoginOutputData loginOutputData) {
                assertEquals("vihaanchugh@gmail.com", loginOutputData.getEmailAddress());
                assertEquals("Vihaan", loginOutputData.getFirstName());
                assertEquals("Chugh", loginOutputData.getLastName());
                assertEquals("Chugh", loginOutputData.getLastName());
                assertEquals("100879585", loginOutputData.getInstructorID());
                assert loginOutputData.getUseCaseSuccess();
                assertNull(loginOutputData.getErrorMessage());
                return null;
            }

            @Override
            public ResponseEntity<Map<String, Object>> prepareFailResponse(LoginOutputData loginOutputData) {
                throw new RuntimeException("Use Case Failure Not Expected");
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(instructorRepository, successPresenter);
        interactor.execute(loginInputData);
    }

    /**
     * Test for failure when no such instructor exists.
     */
    @Test
    void failureNoSuchInstructorExistsTest() {
        LoginInputData loginInputData = new LoginInputData("john.cena@wwe.com");
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public ResponseEntity<Map<String, Object>> prepareSuccessResponse(LoginOutputData loginOutputData) {
                throw new RuntimeException("Use Case Success Not Expected");
            }

            @Override
            public ResponseEntity<Map<String, Object>> prepareFailResponse(LoginOutputData loginOutputData) {
                assert !loginOutputData.getErrorMessage().isBlank();  // test if error message has been set
                assert !loginOutputData.getUseCaseSuccess();
                return null;
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(instructorRepository, successPresenter);
        interactor.execute(loginInputData);
    }

    /**
     * Test for failure when a bad request is sent.
     */
    @Test
    void failureWhenBadRequestTest() {
        LoginInputData loginInputData = new LoginInputData("      ");
        loginInputData.setInstructorEmailAddress("        ");
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public ResponseEntity<Map<String, Object>> prepareSuccessResponse(LoginOutputData loginOutputData) {
                throw new RuntimeException("Use Case Success Not Expected");
            }

            @Override
            public ResponseEntity<Map<String, Object>> prepareFailResponse(LoginOutputData loginOutputData) {
                assert !loginOutputData.getErrorMessage().isBlank();  // test if error message has been set
                loginOutputData.setLastName("Doe");
                loginOutputData.setFirstName("John");
                assert loginOutputData.getFirstName().equals("John");
                assert loginOutputData.getLastName().equals("Doe");
                return null;
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(instructorRepository, successPresenter);
        interactor.execute(loginInputData);
    }

}
