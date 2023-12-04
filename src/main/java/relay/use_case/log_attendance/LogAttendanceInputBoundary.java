package relay.use_case.log_attendance;

import org.springframework.http.ResponseEntity;

public interface LogAttendanceInputBoundary {
	ResponseEntity<?> execute(LogAttendanceInputData inputData);
}
