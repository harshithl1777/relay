package relay.use_case.end_session;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface EndSessionOutputBoundary {
    ResponseEntity<Map<String, Object>> prepareSuccessResponse(EndSessionOutputData endSessionOutputData);

    ResponseEntity<Map<String, Object>> prepareFailResponse(EndSessionOutputData failureendSessionOutputData);

}
