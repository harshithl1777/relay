package relay.interface_adapter.end_session;

import org.springframework.http.HttpStatus;
import relay.interface_adapter.State;

public class EndSessionState implements State {
    private String errorMessage;
    private HttpStatus statusCode;

    @Override
    public String getErrorMessage() {
        return null;
    }

    @Override
    public HttpStatus getStatusCode() {
        return null;
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
