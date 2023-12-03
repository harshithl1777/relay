package relay.use_case.start_session;

import org.springframework.http.ResponseEntity;
import relay.entity.AttendanceRecord;
import relay.entity.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StartSessionInteractor {
    final StartSessionDataAccessInterface sessionDataAccessObject;
    final StartSessionOutputBoundary sessionPresenter;

    public StartSessionInteractor(StartSessionDataAccessInterface sessionDataAccessInterface,
                                   StartSessionOutputBoundary sessionPresenter) {
        this.sessionDataAccessObject = sessionDataAccessInterface;
        this.sessionPresenter = sessionPresenter;
    }

    public ResponseEntity<Map<String, Object>> execute(StartSessionInputData inputData) {
        try {
            List<AttendanceRecord> attendance = new ArrayList<>();
            Session newSession = new Session(attendance, inputData.getCourseID(), inputData.getInstructorID(), inputData.getStartedAt());
            
            sessionDataAccessObject.save(newSession);
            StartSessionOutputData startSessionSuccessOutputData = new StartSessionOutputData(inputData.getCourseID(), inputData.getInstructorID(), inputData.getStartedAt());
            return sessionPresenter.prepareSuccessResponse(startSessionSuccessOutputData);
        } catch (Exception e) {
            StartSessionOutputData startSessionFailureOutputData = new StartSessionOutputData(e.getMessage());
            return sessionPresenter.prepareFailResponse(startSessionFailureOutputData);
        }
    }
}
