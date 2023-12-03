package relay.interface_adapter.LogAttendance;

import org.springframework.http.ResponseEntity;
import relay.use_case.LogAttendance.LogAttendanceInputBoundary;
import relay.use_case.LogAttendance.LogAttendanceInputData;

import java.sql.Timestamp;

public class LogAttendanceController {
    final LogAttendanceInputBoundary logAttendanceUseCaseInteractor;

    public LogAttendanceController(LogAttendanceInputBoundary logAttendanceUseCaseInteractor) {
        this.logAttendanceUseCaseInteractor = logAttendanceUseCaseInteractor;
    }
    public ResponseEntity execute(LogAttendanceInputData inputData) {
        return logAttendanceUseCaseInteractor.execute(inputData);
    }
}
