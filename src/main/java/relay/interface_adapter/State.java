package relay.interface_adapter;

import org.springframework.http.HttpStatus;

public interface State {

	public String getErrorMessage();

	public HttpStatus getStatusCode();

	public void setErrorMessage(String errorMessage);

	public void setStatusCode(HttpStatus statusCode);
}
