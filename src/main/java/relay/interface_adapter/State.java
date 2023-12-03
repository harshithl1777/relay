package relay.interface_adapter;

import org.springframework.http.HttpStatus;

public interface State {

	String getErrorMessage();

	HttpStatus getStatusCode();

	void setErrorMessage(String errorMessage);

	void setStatusCode(HttpStatus statusCode);
}
