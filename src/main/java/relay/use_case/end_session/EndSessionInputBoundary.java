package relay.use_case.end_session;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface EndSessionInputBoundary {
    ResponseEntity<Map<String, Object>> execute(EndSessionInputData endSessionInputData);
}

