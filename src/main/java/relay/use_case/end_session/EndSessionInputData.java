package relay.use_case.end_session;

public class EndSessionInputData {
    final private String sessionID;

    public EndSessionInputData(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionID() {
        return sessionID;
    }
}
