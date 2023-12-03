package relay.interface_adapter.create_course;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import relay.app.SignupUseCaseFactory;
import relay.data_access.FirebaseInstructorDataAccessObject;
import relay.interface_adapter.signup.SignupController;
import relay.interface_adapter.signup.SignupViewModel;
import relay.use_case.signup.SignupInputData;
import relay.use_case.signup.SignupInstructorDataAccessInterface;

import java.util.Map;

public class CreateCourseResponseHandler {

    @PostMapping("/api/courses")
    public ResponseEntity<?> signup(@RequestBody Map<String, Object> requestBody) {
        String courseName = (String) requestBody.get("courseName");
        String instructorID = (String) requestBody.get("instructorID");

        SignupViewModel signupViewModel = new SignupViewModel();

        SignupInstructorDataAccessInterface instructorDataAccessObject = new FirebaseInstructorDataAccessObject();
        SignupController signupController = SignupUseCaseFactory.createSignupUseCase(signupViewModel,
                instructorDataAccessObject);
        SignupInputData requestInputData = new SignupInputData(firstName, lastName, emailAddress);
        return signupController.execute(requestInputData);
    }

}
