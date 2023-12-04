package relay.interface_adapter.end_session;

import java.util.stream.Stream;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import relay.app.EndSessionUseCaseFactory;
import relay.data_access.FirebaseCourseDataAccessObject;
import relay.data_access.FirebaseSessionDataAccessObject;
import relay.use_case.end_session.EndSessionCourseDataAccessInterface;
import relay.use_case.end_session.EndSessionInputData;
import relay.use_case.end_session.EndSessionSessionDataAccessInterface;

@RestController
public class EndSessionResponseHandler {
	@DeleteMapping("/api/sessions/{id}")
	@CrossOrigin(origins = "https://localhost:3000")
	public ResponseEntity<?> endSession(@PathVariable String id) {
		if (!Stream.of(id).allMatch(Objects::nonNull)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}

		EndSessionViewModel endSessionViewModel = new EndSessionViewModel();

		EndSessionCourseDataAccessInterface endSessionCourseDataAccessObject = new FirebaseCourseDataAccessObject();
		EndSessionSessionDataAccessInterface endSessionSessionDataAccessObject = new FirebaseSessionDataAccessObject();

		EndSessionController endSessionController = EndSessionUseCaseFactory.createEndSessionUseCase(
				endSessionViewModel,
				endSessionCourseDataAccessObject, endSessionSessionDataAccessObject);
		EndSessionInputData requestInputData = new EndSessionInputData(id);
		return endSessionController.execute(requestInputData);
	}
}
