package relay.interface_adapter.show_courses;

import org.springframework.http.ResponseEntity;
import relay.use_case.show_courses.ShowCourseInputBoundary;
import relay.use_case.show_courses.ShowCourseInputData;

import java.util.Map;

public class ShowCourseController {
    final ShowCourseInputBoundary showCourseInteractor;

    public ShowCourseController(ShowCourseInputBoundary showCourseInteractor) {
        this.showCourseInteractor = showCourseInteractor;
    }

    public ResponseEntity<Map<String, Object>> execute(ShowCourseInputData inputData) {
            return showCourseInteractor.execute(inputData);
        }

    }
