package relay.exceptions;

public class ResourceNotFoundException extends Exception {
	public ResourceNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
