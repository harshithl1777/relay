package relay.interface_adapter.log_attendance;

import org.springframework.http.ResponseEntity;
import relay.use_case.log_attendance.LogAttendanceInputBoundary;
import relay.use_case.log_attendance.LogAttendanceInputData;

public class LogAttendanceController {
	final LogAttendanceInputBoundary logAttendanceUseCaseInteractor;

	public LogAttendanceController(LogAttendanceInputBoundary logAttendanceUseCaseInteractor) {
		this.logAttendanceUseCaseInteractor = logAttendanceUseCaseInteractor;
	}

	public ResponseEntity<?> execute(LogAttendanceInputData inputData) {
		return logAttendanceUseCaseInteractor.execute(inputData);
	}
}
