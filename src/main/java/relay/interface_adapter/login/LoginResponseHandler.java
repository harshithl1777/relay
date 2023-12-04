package relay.interface_adapter.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import relay.app.LoginUseCaseFactory;
import relay.data_access.FirebaseInstructorDataAccessObject;
import relay.use_case.login.LoginInputData;
import relay.use_case.login.LoginInstructorDataAccessInterface;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * RestController class for handling login responses.
 * This class maps login requests to the corresponding use case, controller, and
 * presenter components.
 */
@RestController
public class LoginResponseHandler {

	/**
	 * Handles login requests.
	 *
	 * @param requestBody The request body containing the email address for the
	 *                    login operation.
	 * @return A ResponseEntity representing the result of the login operation.
	 */
	@GetMapping("/api/instructors")
	@CrossOrigin(origins = "https://localhost:3000")
	public ResponseEntity<?> login(@RequestParam String emailAddress) {
		if (!Stream.of(emailAddress).allMatch(Objects::nonNull)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		LoginViewModel loginViewModel = new LoginViewModel();

		LoginInstructorDataAccessInterface instructorDataAccessObject = new FirebaseInstructorDataAccessObject();
		LoginController loginController = LoginUseCaseFactory.createLoginUseCase(loginViewModel,
				instructorDataAccessObject);
		LoginInputData requestInputData = new LoginInputData(emailAddress);
		return loginController.execute(requestInputData);
	}
}
