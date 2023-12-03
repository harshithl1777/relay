package relay.interface_adapter.show_courses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import relay.data_access.FirebaseCourseDataAccessObject;
import relay.use_case.show_courses.ShowCourseDataAccessInterface;
import relay.app.ShowCourseFactory;
import relay.use_case.show_courses.ShowCourseInputData;

import java.util.Map;

@RestController
public class ShowCourseResponseHandler {

    @GetMapping("/api/instructors/{id}/courses")
    public ResponseEntity<Map<String, Object>> showCourses(@PathVariable String id) {
        ShowCourseViewModel showCourseViewModel = new ShowCourseViewModel();

        ShowCourseDataAccessInterface courseDataAccessObject = new FirebaseCourseDataAccessObject();

        ShowCourseController showCourseController = ShowCourseFactory.createShowCourseUseCase(showCourseViewModel,
                courseDataAccessObject);
        ShowCourseInputData requestInputData = new ShowCourseInputData(id);
        return showCourseController.execute(requestInputData);
    }
}
