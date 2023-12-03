package relay.interface_adapter.end_session;

import org.springframework.http.HttpStatus;
import relay.interface_adapter.State;

public class EndSessionState implements State{
    private String errorMessage;
    private HttpStatus statusCode;

    public EndSessionState(EndSessionState copyState) {
        this.errorMessage = copyState.errorMessage;
        this.statusCode = copyState.statusCode;
    }


    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public HttpStatus getStatusCode() {
        return statusCode;
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
