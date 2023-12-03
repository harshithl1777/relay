package relay.use_case.signup;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface SignupInputBoundary {
	ResponseEntity<Map<String, Object>> execute(SignupInputData signupInputData);
}
