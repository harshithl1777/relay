package relay.interface_adapter.create_course;

import org.springframework.http.ResponseEntity;
import relay.use_case.create_course.CreateCourseInputBoundary;
import relay.use_case.create_course.CreateCourseInputData;

import java.util.Map;

public class CreateCourseController {
    final CreateCourseInputBoundary signupUseCaseInteractor;

    public CreateCourseController(CreateCourseInputBoundary signupUseCaseInteractor) {
        this.signupUseCaseInteractor = signupUseCaseInteractor;
    }

    public ResponseEntity<Map<String, Object>> execute(CreateCourseInputData createCourseInputData) {
        return signupUseCaseInteractor.execute(createCourseInputData);
    }

}