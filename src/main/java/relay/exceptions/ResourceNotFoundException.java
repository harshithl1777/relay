package relay.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
