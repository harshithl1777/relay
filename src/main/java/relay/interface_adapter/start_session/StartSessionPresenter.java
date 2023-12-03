package relay.interface_adapter.start_session;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import relay.interface_adapter.ResponseFactory;
import relay.use_case.start_session.StartSessionOutputBoundary;
import relay.use_case.start_session.StartSessionOutputData;

import java.util.Map;

/**
 * Presenter class for handling the results of the startSession use case.
 * This class updates the startSession view model based on the success or
 * failure output data.
 */
public class StartSessionPresenter implements StartSessionOutputBoundary {

	private final StartSessionViewModel startSessionViewModel;

	/**
	 * Constructs a new instance of StartSessionPresenter with the provided
	 * startSession view model.
	 *
	 * @param startSessionViewModel The view model for the startSession operation.
	 */
	public StartSessionPresenter(StartSessionViewModel startSessionViewModel) {
		this.startSessionViewModel = startSessionViewModel;
	}

	/**
	 * Prepares a success response for the startSession use case.
	 *
	 * @param successStartSessionOutputData The output data containing information
	 *                                      for the successful startSession
	 *                                      operation.
	 * @return A ResponseEntity containing a map of key-value pairs representing the
	 *         success response.
	 */
	@Override
	public ResponseEntity<Map<String, Object>> prepareSuccessResponse(
			StartSessionOutputData successStartSessionOutputData) {
		StartSessionState startSessionState = (StartSessionState) startSessionViewModel.getState();
		startSessionState.setSessionID(successStartSessionOutputData.getSessionID());
		startSessionState.setCourseID(successStartSessionOutputData.getCourseID());
		startSessionState.setInstructorID(successStartSessionOutputData.getInstructorID());
		startSessionState.setStartedAt(successStartSessionOutputData.getStartedAt());
		startSessionState.setSessionCode(successStartSessionOutputData.getSessionCode());
		startSessionState.setAttendance(successStartSessionOutputData.getAttendance());
		startSessionState.setStatusCode(HttpStatus.CREATED);
		return ResponseFactory.createSuccessResponseEntity(startSessionViewModel);
	}

	/**
	 * Prepares a failure response for the startSession use case.
	 *
	 * @param failureStartSessionOutputData The output data containing information
	 *                                      for the failed startSession operation.
	 * @return A ResponseEntity containing a map of key-value pairs representing the
	 *         failure response.
	 */
	@Override
	public ResponseEntity<Map<String, Object>> prepareFailResponse(
			StartSessionOutputData failureStartSessionOutputData) {
		StartSessionState startSessionState = (StartSessionState) startSessionViewModel.getState();
		startSessionState.setErrorMessage(failureStartSessionOutputData.getErrorMessage());
		startSessionState.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseFactory.createFailureResponseEntity(startSessionViewModel);
	}
}
