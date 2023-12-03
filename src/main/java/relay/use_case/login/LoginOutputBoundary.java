package relay.use_case.login;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Interface defining methods for presenting the results of the login use case.
 */
public interface LoginOutputBoundary {

    /**
     * Prepares a success response for the login use case.
     *
     * @param loginOutputData The output data containing information for the successful login operation.
     * @return A ResponseEntity containing a map of key-value pairs representing the success response.
     */
    ResponseEntity<Map<String, Object>> prepareSuccessResponse(LoginOutputData loginOutputData);

    /**
     * Prepares a failure response for the login use case.
     *
     * @param loginOutputData The output data containing information for the failed login operation.
     * @return A ResponseEntity containing a map of key-value pairs representing the failure response.
     */
    ResponseEntity<Map<String, Object>> prepareFailResponse(LoginOutputData loginOutputData);

}
