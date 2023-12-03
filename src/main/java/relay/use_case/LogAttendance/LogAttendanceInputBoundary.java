package relay.use_case.LogAttendance;

import org.springframework.http.ResponseEntity;
import relay.exceptions.ResourceNotFoundException;

public interface LogAttendanceInputBoundary {
    ResponseEntity execute(LogAttendanceInputData inputData);
}
