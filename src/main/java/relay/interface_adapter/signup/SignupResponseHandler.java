package relay.interface_adapter.signup;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import relay.app.SignupUseCaseFactory;
import relay.data_access.FirebaseInstructorDataAccessObject;
import relay.use_case.signup.SignupInputData;
import relay.use_case.signup.SignupInstructorDataAccessInterface;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
public class SignupResponseHandler {

	@PostMapping("/api/users")
	public ResponseEntity<?> signup(@RequestBody Map<String, Object> requestBody) {
		String firstName = (String) requestBody.get("firstName");
		String lastName = (String) requestBody.get("lastName");
		String emailAddress = (String) requestBody.get("emailAddress");

		if (!Stream.of(firstName, lastName, emailAddress).allMatch(Objects::nonNull)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}

		SignupViewModel signupViewModel = new SignupViewModel();

		SignupInstructorDataAccessInterface instructorDataAccessObject = new FirebaseInstructorDataAccessObject();
		SignupController signupController = SignupUseCaseFactory.createSignupUseCase(signupViewModel,
				instructorDataAccessObject);
		SignupInputData requestInputData = new SignupInputData(firstName, lastName, emailAddress);
		return signupController.execute(requestInputData);
	}

}
