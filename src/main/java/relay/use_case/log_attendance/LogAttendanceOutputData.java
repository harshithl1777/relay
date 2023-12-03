package relay.use_case.log_attendance;

public class LogAttendanceOutputData {
    private boolean useCaseSuccess;
    private String errorMessage;

    public LogAttendanceOutputData() {
        this.useCaseSuccess = true;
    }

    public LogAttendanceOutputData(String errorMessage) {
        this.useCaseSuccess = false;
        this.errorMessage = errorMessage;
    }

    public boolean getUseCaseSuccess() {
        return useCaseSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
