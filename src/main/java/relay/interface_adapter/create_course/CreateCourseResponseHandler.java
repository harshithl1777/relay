package relay.interface_adapter.create_course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import relay.app.CreateCourseUseCaseFactory;
import relay.data_access.FirebaseCourseCourseDataAccessObject;
import relay.use_case.create_course.CreateCourseCourseDataAccessInterface;
import relay.use_case.create_course.CreateCourseInputData;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
public class CreateCourseResponseHandler {

    @PostMapping("/api/courses")
    public ResponseEntity<?> signup(@RequestBody Map<String, Object> requestBody) {
        String courseName = (String) requestBody.get("courseName");
        String instructorID = (String) requestBody.get("instructorID");

        if (!Stream.of(courseName, instructorID).allMatch(Objects::nonNull)) {
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }


        CreateCourseViewModel createCourseViewModel = new CreateCourseViewModel();

        CreateCourseCourseDataAccessInterface courseDataAccessObject = new FirebaseCourseCourseDataAccessObject();

        CreateCourseController createCourseController = CreateCourseUseCaseFactory.createCreateCourseUseCase(createCourseViewModel,
                courseDataAccessObject);
        CreateCourseInputData requestInputData = new CreateCourseInputData(courseName, instructorID);
        return createCourseController.execute(requestInputData);
    }

}
