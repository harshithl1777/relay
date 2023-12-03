package relay.use_case.LogAttendance;

import org.springframework.http.ResponseEntity;

public interface LogAttendanceOutputBoundary {
    ResponseEntity PrepareSuccessResponse(LogAttendanceOutputData outputData);
    ResponseEntity PrepareFailResponse(LogAttendanceOutputData outputData);
}
