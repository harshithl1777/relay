package relay.interface_adapter.login;

import org.springframework.http.ResponseEntity;
import relay.use_case.login.LoginInputBoundary;
import relay.use_case.login.LoginInputData;

import java.util.Map;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    public ResponseEntity<Map<String, Object>> execute(LoginInputData loginInputData) {
        return loginUseCaseInteractor.execute(loginInputData);
    }
}


