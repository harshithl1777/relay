package relay.use_case.start_session;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface StartSessionOutputBoundary {
	ResponseEntity<Map<String, Object>> prepareSuccessResponse(StartSessionOutputData startSessionOutputData);

	ResponseEntity<Map<String, Object>> prepareFailResponse(StartSessionOutputData failurestartSessionOutputData);
}
