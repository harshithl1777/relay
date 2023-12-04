package relay.use_case.login;

import org.springframework.http.ResponseEntity;
import relay.entity.Instructor;
import relay.exceptions.ResourceNotFoundException;

import java.util.Map;

/**
 * Implementation of the {@link LoginInputBoundary} interface for the login use case.
 * This class orchestrates the interaction between the login use case, the data access layer,
 * and the presentation layer by using the provided data access object and presenter.
 */
public class LoginInteractor implements LoginInputBoundary {

    private final LoginInstructorDataAccessInterface instructorDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginInstructorDataAccessInterface instructorDataAccessObject, LoginOutputBoundary loginPresenter) {
        this.instructorDataAccessObject = instructorDataAccessObject;
        this.loginPresenter = loginPresenter;
    }

    /**
     * Executes the login use case based on the provided login input data.
     *
     * @param loginInputData The input data containing information for the login operation.
     * @return A ResponseEntity containing a map of key-value pairs representing the result of the login operation.
     */
    @Override
    public ResponseEntity<Map<String, Object>> execute(LoginInputData loginInputData) {
        try {
            Instructor loggedInInstructor = instructorDataAccessObject.readByEmailAddress(loginInputData.getInstructorEmailAddress());

            LoginOutputData loginSuccessOutputData = new LoginOutputData(
                    loggedInInstructor.getInstructorID(),
                    loggedInInstructor.getFirstName(),
                    loggedInInstructor.getLastName(),
                    loggedInInstructor.getEmailAddress()
            );
            loginSuccessOutputData.setUseCaseSuccess(true);
            return loginPresenter.prepareSuccessResponse(loginSuccessOutputData);
        } catch (ResourceNotFoundException e) {
            LoginOutputData loginFailureOutputData = new LoginOutputData(e.getMessage());
            return loginPresenter.prepareFailResponse(loginFailureOutputData);
        }
    }
}
