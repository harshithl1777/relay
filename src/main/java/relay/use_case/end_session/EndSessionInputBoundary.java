package relay.use_case.end_session;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface EndSessionInputBoundary {
    ResponseEntity<HttpStatus> execute(EndSessionInputData endSessionInputData);
}

