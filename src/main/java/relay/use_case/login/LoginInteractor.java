package relay.use_case.login;

import org.springframework.http.ResponseEntity;
import relay.entity.Instructor;
import relay.exceptions.ResourceAlreadyExistsException;
import relay.exceptions.ResourceNotFoundException;
import relay.use_case.signup.SignupOutputData;

import java.util.Map;

public class LoginInteractor implements LoginInputBoundary {
    final LoginInstructorDataAccessInterface instructorDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginInstructorDataAccessInterface instructorDataAccessObject, LoginOutputBoundary loginPresenter) {
        this.instructorDataAccessObject = instructorDataAccessObject;
        this.loginPresenter = loginPresenter;
    }


    @Override
    public ResponseEntity<Map<String, Object>> execute(LoginInputData loginInputData) {
        try {
            Instructor loggedInInstructor = instructorDataAccessObject.readByEmailAddress(loginInputData.getInstructorEmailAddress());

            LoginOutputData loginSuccessOutputData = new LoginOutputData(loggedInInstructor.getInstructorID(), loggedInInstructor.getFirstName(), loggedInInstructor.getLastName(), loggedInInstructor.getEmailAddress());
            return loginPresenter.prepareSuccessResponse(loginSuccessOutputData);
        } catch (ResourceNotFoundException e) {
            LoginOutputData loginFailureOutputData = new LoginOutputData(e.getMessage());
            return loginPresenter.prepareFailResponse(loginFailureOutputData);
        }

    }
}
