package relay.interface_adapter.signup;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import relay.use_case.signup.SignupInputBoundary;
import relay.use_case.signup.SignupInputData;

public class SignupController {
	final SignupInputBoundary signupUseCaseInteractor;

	public SignupController(SignupInputBoundary signupUseCaseInteractor) {
		this.signupUseCaseInteractor = signupUseCaseInteractor;
	}

	public ResponseEntity<Map<String, Object>> execute(SignupInputData signupInputData) {
		return signupUseCaseInteractor.execute(signupInputData);
	}

}
