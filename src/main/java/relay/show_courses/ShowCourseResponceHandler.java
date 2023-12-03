package relay.show_courses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import relay.data_access.FirebaseCourseDataAccessObject;
import java.util.Map;

public class ShowCourseResponceHandler {

    @GetMapping("/api/instructors/{id}/courses")
            public ResponseEntity<Map<String, Object>> showCourses(@PathVariable String instructorID) {

        ShowCourseViewModel showCourseViewModel = new ShowCourseViewModel();

        FirebaseCourseDataObjectInterface courseDataAccessObject = new FirebaseCourseDataAccessObject();

        ShowCourseController showCourseController = ShowCourseFactory.createShowCourseUseCase(showCourseViewModel,
                courseDataAccessObject);
        ShowCourseInputData requestInputData = new ShowCourseInputData(instructorID);
        return showCourseController.execute(requestInputData);
    }
}
