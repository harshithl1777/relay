package relay.interface_adapter.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import relay.interface_adapter.ResponseFactory;
import relay.use_case.login.LoginOutputBoundary;
import relay.use_case.login.LoginOutputData;

import java.util.Map;

/**
 * Presenter class for handling the results of the login use case.
 * This class updates the login view model based on the success or failure
 * output data.
 */
public class LoginPresenter implements LoginOutputBoundary {

	private final LoginViewModel loginViewModel;

	/**
	 * Constructs a new instance of LoginPresenter with the provided login view
	 * model.
	 *
	 * @param loginViewModel The view model for the login operation.
	 */
	public LoginPresenter(LoginViewModel loginViewModel) {
		this.loginViewModel = loginViewModel;
	}

	/**
	 * Prepares a success response for the login use case.
	 *
	 * @param successLoginOutputData The output data containing information for the
	 *                               successful login operation.
	 * @return A ResponseEntity containing a map of key-value pairs representing the
	 *         success response.
	 */
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

	/**
	 * Prepares a failure response for the login use case.
	 *
	 * @param failureLoginOutputData The output data containing information for the
	 *                               failed login operation.
	 * @return A ResponseEntity containing a map of key-value pairs representing the
	 *         failure response.
	 */
	@Override
	public ResponseEntity<Map<String, Object>> prepareFailResponse(LoginOutputData failureLoginOutputData) {
		LoginState loginState = (LoginState) loginViewModel.getState();
		loginState.setErrorMessage(failureLoginOutputData.getErrorMessage());
		loginState.setStatusCode(HttpStatus.UNAUTHORIZED);
		return ResponseFactory.createFailureResponseEntity(loginViewModel);
	}
}
