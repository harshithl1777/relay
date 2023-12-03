package relay.use_case.end_session;

public class EndSessionOutputData {
    private boolean useCaseSuccess;
    private String errorMessage;

    private String sessionID;

    public EndSessionOutputData() {
        this.useCaseSuccess = true;
    }

    public EndSessionOutputData(String errorMessage) {
        this.useCaseSuccess = false;
        this.errorMessage = errorMessage;

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSessionID() {
        return sessionID;
    }

    public boolean getUseCaseSuccess() {
        return useCaseSuccess;
    }
}
