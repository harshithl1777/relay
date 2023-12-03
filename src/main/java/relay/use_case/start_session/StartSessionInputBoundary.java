package relay.use_case.start_session;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface StartSessionInputBoundary {
    ResponseEntity<Map<String, Object>> execute(StartSessionInputData startSessionInputData);

}
