package relay.use_case.signup;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface SignupOutputBoundary {
	ResponseEntity<Map<String, Object>> prepareSuccessResponse(SignupOutputData successSignupOutputData);

	ResponseEntity<Map<String, Object>> prepareFailResponse(SignupOutputData failureSignupOutputData);
}
