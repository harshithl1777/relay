package relay.use_case.LogAttendance;

import org.springframework.http.ResponseEntity;
import relay.entity.AttendanceRecord;
import relay.entity.AttendanceRecordFactory;
import relay.entity.Session;
import relay.exceptions.ResourceNotFoundException;
import relay.use_case.signup.SignupOutputData;

public class LogAttendanceInteractor implements LogAttendanceInputBoundary{
    final LogAttendanceSessionDataAccessInterface sessionDataAccessObject;
    final LogAttendanceOutputBoundary sessionPresenter;
    final AttendanceRecordFactory attendanceRecordFactory;

    public LogAttendanceInteractor(LogAttendanceSessionDataAccessInterface sessionDataAccessInterface,
                                   LogAttendanceOutputBoundary sessionPresenter, AttendanceRecordFactory attendanceRecordFactory) {
        this.sessionDataAccessObject = sessionDataAccessInterface;
        this.sessionPresenter = sessionPresenter;
        this.attendanceRecordFactory = attendanceRecordFactory;
    }
    @Override
    public ResponseEntity execute(LogAttendanceInputData inputData) {
        try {
            String sessionID = inputData.getSessionID();
            Session session = sessionDataAccessObject.read(sessionID);
            AttendanceRecord attendanceRecord = attendanceRecordFactory.createAttendanceRecord(inputData.getStudentFirstName(), inputData.getStudentLastName(), inputData.getStudentID(), inputData.getStudentEmail());
            session.getAttendance().add(attendanceRecord);
            sessionDataAccessObject.save(session);

            LogAttendanceOutputData logAttendanceSuccessOutputData = new LogAttendanceOutputData();

            return sessionPresenter.PrepareSuccessResponse(logAttendanceSuccessOutputData);
        } catch (Exception e) {
            LogAttendanceOutputData logAttendanceFailureOutputData = new LogAttendanceOutputData(e.getMessage());
            return sessionPresenter.PrepareFailResponse(logAttendanceFailureOutputData);
        }
    }
}
