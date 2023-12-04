package relay.interface_adapter.start_session;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import relay.app.StartSessionUseCaseFactory;
import relay.data_access.FirebaseCourseCourseDataAccessObject;
import relay.data_access.FirebaseInstructorDataAccessObject;
import relay.data_access.FirebaseSessionDataAccessObject;
import relay.use_case.start_session.StartSessionSessionDataAccessInterface;
import relay.use_case.start_session.StartSessionCourseDataAccessInterface;
import relay.use_case.start_session.StartSessionInputData;
import relay.use_case.start_session.StartSessionInstructorDataAccessInterface;

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
		StartSessionSessionDataAccessInterface sessionDataAccessObject = new FirebaseSessionDataAccessObject();
		StartSessionCourseDataAccessInterface courseDataAccessObject = new FirebaseCourseCourseDataAccessObject();
		StartSessionInstructorDataAccessInterface instructorDataAccessObject = new FirebaseInstructorDataAccessObject();
		StartSessionController startSessionController = StartSessionUseCaseFactory.createStartSessionUseCase(
				startSessionViewModel,
				sessionDataAccessObject, courseDataAccessObject, instructorDataAccessObject);
		StartSessionInputData requestInputData = new StartSessionInputData(courseID, instructorID);
		return startSessionController.execute(requestInputData);
	}
}
