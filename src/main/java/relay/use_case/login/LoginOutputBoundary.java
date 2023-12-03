package relay.use_case.login;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoginOutputBoundary {
    ResponseEntity<Map<String, Object>> prepareSuccessResponse(LoginOutputData loginOutputData);

    ResponseEntity<Map<String, Object>> prepareFailResponse(LoginOutputData loginOutputData);


}
