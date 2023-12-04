package relay.use_case.end_session;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface EndSessionInputBoundary {
	ResponseEntity<HttpStatus> execute(EndSessionInputData endSessionInputData);
}
