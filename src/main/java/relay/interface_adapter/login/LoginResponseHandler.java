package relay.interface_adapter.login;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import relay.app.LoginUseCaseFactory;
import relay.app.SignupUseCaseFactory;
import relay.data_access.FirebaseInstructorDataAccessObject;
import relay.use_case.login.LoginInputData;
import relay.use_case.login.LoginInstructorDataAccessInterface;

@RestController
public class LoginResponseHandler {

    @GetMapping("/api/users")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> requestBody) {
        String emailAddress = (String) requestBody.get("emailAddress");

        if (emailAddress == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }

        LoginViewModel loginViewModel = new LoginViewModel();

        LoginInstructorDataAccessInterface instructorDataAccessObject = new FirebaseInstructorDataAccessObject();
        LoginController loginController = LoginUseCaseFactory.createLoginUseCase(loginViewModel,
                instructorDataAccessObject);
        LoginInputData requestInputData = new LoginInputData(emailAddress);
        return loginController.execute(requestInputData);
    }

}
