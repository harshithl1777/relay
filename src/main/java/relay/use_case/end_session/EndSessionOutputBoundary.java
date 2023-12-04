package relay.use_case.end_session;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface EndSessionOutputBoundary {
	ResponseEntity<HttpStatus> prepareSuccessResponse(EndSessionOutputData endSessionOutputData);

	ResponseEntity<HttpStatus> prepareFailResponse(EndSessionOutputData failureendSessionOutputData);

}
