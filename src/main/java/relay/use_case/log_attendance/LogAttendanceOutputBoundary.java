package relay.use_case.log_attendance;

import org.springframework.http.ResponseEntity;

public interface LogAttendanceOutputBoundary {
	ResponseEntity<?> prepareSuccessResponse(LogAttendanceOutputData outputData);

	ResponseEntity<?> prepareFailResponse(LogAttendanceOutputData outputData);
}
