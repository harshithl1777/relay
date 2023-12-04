package relay.interface_adapter.log_attendance;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import relay.interface_adapter.ResponseFactory;
import relay.use_case.log_attendance.LogAttendanceOutputBoundary;
import relay.use_case.log_attendance.LogAttendanceOutputData;

public class LogAttendancePresenter implements LogAttendanceOutputBoundary {

	private LogAttendanceViewModel logAttendanceViewModel;

	public LogAttendancePresenter(LogAttendanceViewModel viewModel) {
		this.logAttendanceViewModel = viewModel;
	}

	@Override
	public ResponseEntity<?> prepareSuccessResponse(LogAttendanceOutputData outputData) {
		LogAttendanceState state = (LogAttendanceState) logAttendanceViewModel.getState();
		state.setStatusCode(HttpStatus.CREATED);
		return ResponseFactory.createSuccessResponseEntity(logAttendanceViewModel);
	}

	@Override
	public ResponseEntity<?> prepareFailResponse(LogAttendanceOutputData outputData) {
		LogAttendanceState state = (LogAttendanceState) logAttendanceViewModel.getState();
		state.setErrorMessage(outputData.getErrorMessage());
		state.setStatusCode(HttpStatus.NOT_FOUND);
		return ResponseFactory.createFailureResponseEntity(logAttendanceViewModel);
	}

}
