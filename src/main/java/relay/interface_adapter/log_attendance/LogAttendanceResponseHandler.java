package relay.interface_adapter.log_attendance;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import relay.app.LogAttendanceUseCaseFactory;
import relay.data_access.FirebaseSessionDataAccessObject;
import relay.use_case.log_attendance.LogAttendanceInputData;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
public class LogAttendanceResponseHandler {

    @PostMapping("/api/session/{id}/attendance")
    public ResponseEntity<?> logAttendance(@PathVariable String id, @RequestBody Map<String, Object> requestBody) {
        String sessionID = id;
        String studentFirstName = (String) requestBody.get("studentFirstName");
        String studentLastName = (String) requestBody.get("studentLastName");
        String studentID = (String) requestBody.get("studentID");
        String studentEmail = (String) requestBody.get("studentEmail");

        Timestamp createdAt = StringToDate((String) requestBody.get("createdAt"));

        if (!Stream.of(sessionID, studentFirstName, studentLastName, studentID, studentEmail, createdAt).allMatch(Objects::nonNull)) {
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }

        LogAttendanceViewModel logAttendanceViewModel = new LogAttendanceViewModel();

        FirebaseSessionDataAccessObject sessionDataAccessObject = new FirebaseSessionDataAccessObject();

        LogAttendanceController logAttendanceController = LogAttendanceUseCaseFactory.createLogAttendanceUseCase(logAttendanceViewModel, sessionDataAccessObject);
        LogAttendanceInputData requestInputData = new LogAttendanceInputData(sessionID, studentFirstName, studentLastName, studentID, studentEmail, createdAt);
        return logAttendanceController.execute(requestInputData);
    }
    private static Timestamp StringToDate(String s) {
        Timestamp ts = Timestamp.valueOf(s);
        return ts;
    }
}
