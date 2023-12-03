package relay.app;

import relay.entity.AttendanceRecordFactory;
import relay.interface_adapter.log_attendance.LogAttendanceController;
import relay.interface_adapter.log_attendance.LogAttendancePresenter;
import relay.interface_adapter.log_attendance.LogAttendanceViewModel;
import relay.use_case.log_attendance.LogAttendanceInputBoundary;
import relay.use_case.log_attendance.LogAttendanceInteractor;
import relay.use_case.log_attendance.LogAttendanceOutputBoundary;
import relay.use_case.log_attendance.LogAttendanceSessionDataAccessInterface;

public class LogAttendanceUseCaseFactory {
	private LogAttendanceUseCaseFactory() {
	}

	public static LogAttendanceController createLogAttendanceUseCase(LogAttendanceViewModel logAttendanceViewModel,
			LogAttendanceSessionDataAccessInterface logAttendanceSessionDataAccessInterface) {
		LogAttendanceOutputBoundary logAttendancePresenter = new LogAttendancePresenter(logAttendanceViewModel);
		AttendanceRecordFactory attendanceRecordFactory = new AttendanceRecordFactory();
		LogAttendanceInputBoundary logAttendanceInteractor = new LogAttendanceInteractor(
				logAttendanceSessionDataAccessInterface, logAttendancePresenter, attendanceRecordFactory);
		return new LogAttendanceController(logAttendanceInteractor);
	}
}
