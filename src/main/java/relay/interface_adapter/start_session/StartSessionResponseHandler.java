package relay.interface_adapter.start_session;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import relay.app.SignupUseCaseFactory;
import relay.app.StartSessionUseCaseFactory;
import relay.data_access.FirebaseInstructorDataAccessObject;
import relay.data_access.FirebaseSessionDataAccessObject;
import relay.interface_adapter.signup.SignupController;
import relay.use_case.signup.SignupInputData;
import relay.use_case.signup.SignupInstructorDataAccessInterface;
import relay.use_case.start_session.StartSessionDataAccessInterface;
import relay.use_case.start_session.StartSessionInputData;

@RestController
public class StartSessionResponseHandler {
	@PostMapping("/api/sessions")
	ResponseEntity<?> startSession(@RequestBody Map<String, Object> requestBody) {
		String courseID = (String) requestBody.get("courseID");
		String instructorID = (String) requestBody.get("instructorID");

		if (!Stream.of(courseID, instructorID).allMatch(Objects::nonNull)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}

		StartSessionViewModel startSessionViewModel = new StartSessionViewModel();
		StartSessionDataAccessInterface startSessionDataAccessObject = new FirebaseSessionDataAccessObject();
		StartSessionController startSessionController = StartSessionUseCaseFactory.createStartSessionUseCase(
				startSessionViewModel,
				startSessionDataAccessObject);
		StartSessionInputData requestInputData = new StartSessionInputData(courseID, instructorID);
		return startSessionController.execute(requestInputData);
	}
}
