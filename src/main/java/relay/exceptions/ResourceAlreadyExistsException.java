package relay.exceptions;

public class ResourceAlreadyExistsException extends Exception {
	public ResourceAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
}
