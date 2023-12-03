package relay.use_case.log_attendance;

import org.springframework.http.ResponseEntity;

public interface LogAttendanceOutputBoundary {
    ResponseEntity PrepareSuccessResponse(LogAttendanceOutputData outputData);
    ResponseEntity PrepareFailResponse(LogAttendanceOutputData outputData);
}
