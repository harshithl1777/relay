package relay.interface_adapter.signup;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import relay.interface_adapter.ResponseFactory;
import relay.use_case.signup.SignupOutputBoundary;
import relay.use_case.signup.SignupOutputData;

public class SignupPresenter implements SignupOutputBoundary {
	private final SignupViewModel signupViewModel;

	public SignupPresenter(SignupViewModel signupViewModel) {
		this.signupViewModel = signupViewModel;
	}

	@Override
	public ResponseEntity<Map<String, Object>> prepareSuccessResponse(SignupOutputData successSignupOutputData) {
		SignupState signupState = (SignupState) signupViewModel.getState();
		signupState.setFirstName(successSignupOutputData.getFirstName());
		signupState.setLastName(successSignupOutputData.getLastName());
		signupState.setEmailAddress(successSignupOutputData.getEmailAddress());
		signupState.setInstructorID(successSignupOutputData.getInstructorID());
		signupState.setStatusCode(HttpStatus.CREATED);
		return ResponseFactory.createSuccessResponseEntity(signupViewModel);
	}

	@Override
	public ResponseEntity<Map<String, Object>> prepareFailResponse(SignupOutputData failureSignupOutputData) {
		SignupState signupState = (SignupState) signupViewModel.getState();
		signupState.setErrorMessage(failureSignupOutputData.getErrorMessage());
		signupState.setStatusCode(HttpStatus.CONFLICT);
		return ResponseFactory.createFailureResponseEntity(signupViewModel);
	}

}
