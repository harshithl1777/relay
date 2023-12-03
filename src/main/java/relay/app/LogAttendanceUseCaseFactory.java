package relay.app;

import relay.entity.AttendanceRecordFactory;
import relay.interface_adapter.LogAttendance.LogAttendanceController;
import relay.interface_adapter.LogAttendance.LogAttendancePresenter;
import relay.interface_adapter.LogAttendance.LogAttendanceViewModel;
import relay.use_case.LogAttendance.LogAttendanceInputBoundary;
import relay.use_case.LogAttendance.LogAttendanceInteractor;
import relay.use_case.LogAttendance.LogAttendanceOutputBoundary;
import relay.use_case.LogAttendance.LogAttendanceSessionDataAccessInterface;

public class LogAttendanceUseCaseFactory {
    private LogAttendanceUseCaseFactory() {
    }

    public static LogAttendanceController createLogAttendanceUseCase(LogAttendanceViewModel logAttendanceViewModel, LogAttendanceSessionDataAccessInterface logAttendanceSessionDataAccessInterface) {
        LogAttendanceOutputBoundary logAttendancePresenter = new LogAttendancePresenter(logAttendanceViewModel);
        AttendanceRecordFactory attendanceRecordFactory = new AttendanceRecordFactory();
        LogAttendanceInputBoundary logAttendanceInteractor = new LogAttendanceInteractor(logAttendanceSessionDataAccessInterface, logAttendancePresenter, attendanceRecordFactory);
        return new LogAttendanceController(logAttendanceInteractor);
    }
}
