package relay.use_case.log_attendance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import relay.entity.AttendanceRecord;
import relay.entity.AttendanceRecordFactory;
import relay.entity.Session;
import relay.entity.SessionFactory;
import relay.exceptions.ResourceAlreadyExistsException;
import relay.exceptions.ResourceNotFoundException;
import relay.use_case.log_attendance.LogAttendanceInteractor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link LogAttendanceInteractor} class.
 */
public class LogAttendanceInteractorTest {
    LogAttendanceSessionDataAccessInterface sessionRepository;

    @BeforeEach
    void setupAttendanceRepository() {
        this.sessionRepository = new InMemorySessionDataAccessObject();
    }

    @Test
    void successTest() throws ResourceNotFoundException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SessionFactory sessionFactory = new SessionFactory();
        Session session = sessionFactory.createSession("1012", "100879585");
        session.setSessionID("1012");
        sessionRepository.save(session);

        LogAttendanceInputData logAttendanceInputData = new LogAttendanceInputData("1012", "John", "Doe", "100879585", "john@gmail.com", timestamp);
        String sessionID = logAttendanceInputData.getSessionID();

        Session session1 = sessionRepository.read(sessionID);
        AttendanceRecordFactory attendanceRecordFactory = new AttendanceRecordFactory();
        AttendanceRecord attendanceRecord = attendanceRecordFactory.createAttendanceRecord(logAttendanceInputData.getStudentFirstName(), logAttendanceInputData.getStudentLastName(), logAttendanceInputData.getStudentID(), logAttendanceInputData.getStudentEmail());
        session1.getAttendance().add(attendanceRecord);
        sessionRepository.save(session1);

        LogAttendanceOutputBoundary successPresenter = new LogAttendanceOutputBoundary() {
            @Override
            public ResponseEntity PrepareSuccessResponse(LogAttendanceOutputData logAttendanceOutputData) {
                assertTrue(logAttendanceOutputData.getUseCaseSuccess());
                return null;
            }

            @Override
            public ResponseEntity PrepareFailResponse(LogAttendanceOutputData logAttendanceOutputData) {
                throw new RuntimeException("Use Case Failure Not Expected");
            }
        };
        LogAttendanceInteractor logAttendanceInteractor = new LogAttendanceInteractor(sessionRepository, successPresenter, attendanceRecordFactory);
        logAttendanceInteractor.execute(logAttendanceInputData);
    }

    @Test
    void failureTest() throws ResourceNotFoundException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SessionFactory sessionFactory = new SessionFactory();
        Session session = sessionFactory.createSession("1012", "100879585");
        session.setSessionID("1012");
        sessionRepository.save(session);

        LogAttendanceInputData logAttendanceInputData = new LogAttendanceInputData("1012", "John", "Doe", "100879585", "john@gmail.com", timestamp);
        String sessionID = logAttendanceInputData.getSessionID();

        AttendanceRecordFactory attendanceRecordFactory = new AttendanceRecordFactory();
        AttendanceRecord attendanceRecord = attendanceRecordFactory.createAttendanceRecord(logAttendanceInputData.getStudentFirstName(), logAttendanceInputData.getStudentLastName(), logAttendanceInputData.getStudentID(), logAttendanceInputData.getStudentEmail());
        LogAttendanceOutputBoundary failurePresenter = new LogAttendanceOutputBoundary() {
            @Override
            public ResponseEntity PrepareSuccessResponse(LogAttendanceOutputData logAttendanceOutputData) {
                throw new RuntimeException("Use Case Success Not Expected");
            }

            @Override
            public ResponseEntity PrepareFailResponse(LogAttendanceOutputData logAttendanceOutputData) {
                assertFalse(logAttendanceOutputData.getUseCaseSuccess());
                return null;
            }
        };
        LogAttendanceInputBoundary interactor = new LogAttendanceInteractor(sessionRepository, failurePresenter, attendanceRecordFactory);
        interactor.execute(logAttendanceInputData);
    }
}
