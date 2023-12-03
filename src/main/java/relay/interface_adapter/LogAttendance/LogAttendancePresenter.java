package relay.interface_adapter.LogAttendance;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import relay.interface_adapter.ResponseFactory;
import relay.use_case.LogAttendance.LogAttendanceOutputBoundary;
import relay.use_case.LogAttendance.LogAttendanceOutputData;

public class LogAttendancePresenter implements LogAttendanceOutputBoundary{

    private LogAttendanceViewModel logAttendanceViewModel;

    public LogAttendancePresenter(LogAttendanceViewModel viewModel) {
        this.logAttendanceViewModel = viewModel;
    }

    @Override
    public ResponseEntity PrepareSuccessResponse(LogAttendanceOutputData outputData) {
        LogAttendanceState state = (LogAttendanceState) logAttendanceViewModel.getState();
        state.setStatusCode(HttpStatus.CREATED);
        return ResponseFactory.createSuccessResponseEntity(logAttendanceViewModel);
    }

    @Override
    public ResponseEntity PrepareFailResponse(LogAttendanceOutputData outputData) {
        LogAttendanceState state = (LogAttendanceState) logAttendanceViewModel.getState();
        state.setErrorMessage(outputData.getErrorMessage());
        state.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseFactory.createFailureResponseEntity(logAttendanceViewModel);
    }

}
