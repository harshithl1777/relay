package relay.use_case.end_session;

public class EndSessionOutputData {
    private boolean useCaseSuccess;
    private String errorMessage;

    private String sessionID;

    public EndSessionOutputData(boolean useCaseSuccess, String errorMessage, String sessionID) {
        this.useCaseSuccess = true;
        this.errorMessage = errorMessage;
        this.sessionID = sessionID;
    }

    public EndSessionOutputData(String errorMessage, String sessionID) {
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
