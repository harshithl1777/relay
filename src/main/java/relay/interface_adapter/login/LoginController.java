package relay.interface_adapter.login;

import org.springframework.http.ResponseEntity;
import relay.use_case.login.LoginInputBoundary;
import relay.use_case.login.LoginInputData;

import java.util.Map;

/**
 * Controller class for handling login requests.
 * This class delegates the execution of the login use case to the provided input boundary.
 */
public class LoginController {

    private final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the login use case based on the provided login input data.
     *
     * @param loginInputData The input data containing information for the login operation.
     * @return A ResponseEntity containing a map of key-value pairs representing the result of the login operation.
     */
    public ResponseEntity<Map<String, Object>> execute(LoginInputData loginInputData) {
        return loginUseCaseInteractor.execute(loginInputData);
    }
}
