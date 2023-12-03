package relay.interface_adapter.login;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import relay.interface_adapter.ResponseFactory;
import relay.use_case.login.LoginOutputBoundary;
import relay.use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;

    public LoginPresenter(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    @Override
    public ResponseEntity<Map<String, Object>> prepareSuccessResponse(LoginOutputData successLoginOutputData) {
        LoginState loginState = (LoginState) loginViewModel.getState();
        loginState.setFirstName(successLoginOutputData.getFirstName());
        loginState.setLastName(successLoginOutputData.getLastName());
        loginState.setEmailAddress(successLoginOutputData.getEmailAddress());
        loginState.setInstructorID(successLoginOutputData.getInstructorID());
        loginState.setStatusCode(HttpStatus.OK);
        return ResponseFactory.createSuccessResponseEntity(loginViewModel);
    }

    @Override
    public ResponseEntity<Map<String, Object>> prepareFailResponse(LoginOutputData failureLoginOutputData) {
        LoginState loginState = (LoginState) loginViewModel.getState();
        loginState.setErrorMessage(failureLoginOutputData.getErrorMessage());
        loginState.setStatusCode(HttpStatus.CONFLICT);
        return ResponseFactory.createFailureResponseEntity(loginViewModel);
    }

}
