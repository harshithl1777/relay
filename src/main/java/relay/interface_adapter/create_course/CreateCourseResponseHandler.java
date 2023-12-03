package relay.interface_adapter.create_course;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import relay.app.SignupUseCaseFactory;
import relay.data_access.FirebaseCourseDataAccessObject;
import relay.data_access.FirebaseInstructorDataAccessObject;
import relay.use_case.createcourse.CreateCourseDataAccessInterface;
import relay.use_case.signup.SignupInputData;
import relay.use_case.signup.SignupInstructorDataAccessInterface;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
public class CreateCourseResponseHandler {

    @PostMapping("/api/courses")
    public ResponseEntity<?> signup(@RequestBody Map<String, Object> requestBody) {
        String courseName = (String) requestBody.get("courseName");
        String instructorID = (String) requestBody.get("instructorID");
        if (Stream.of(courseName, instructorID).anyMatch(Objects::nonNull)) {
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }


        CreateCourseViewModel createCourseViewModel = new CreateCourseViewModel();

        CreateCourseDataAccessInterface courseDataAccessObject = new FirebaseCourseDataAccessObject();

        SignupController signupController = SignupUseCaseFactory.createSignupUseCase(signupViewModel,
                instructorDataAccessObject);
        SignupInputData requestInputData = new SignupInputData(firstName, lastName, emailAddress);
        return signupController.execute(requestInputData);
    }

}
