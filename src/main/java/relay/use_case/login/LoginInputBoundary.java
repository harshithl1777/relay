package relay.use_case.login;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Interface representing the input boundary for the login use case.
 */
public interface LoginInputBoundary {

    /**
     * Executes the login use case based on the provided login input data.
     *
     * @param loginInputData The input data containing information for the login operation.
     * @return A ResponseEntity containing a map of key-value pairs representing the result of the login operation.
     */
    ResponseEntity<Map<String, Object>> execute(LoginInputData loginInputData);

}
