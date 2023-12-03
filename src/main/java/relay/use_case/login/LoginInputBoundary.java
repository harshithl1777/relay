package relay.use_case.login;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoginInputBoundary {
    ResponseEntity<Map<String, Object>> execute(LoginInputData loginInputData);

}
