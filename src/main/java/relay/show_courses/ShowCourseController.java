package relay.show_courses;

import org.springframework.http.ResponseEntity;
import relay.use_case.ExportCSVInputBoundary;
import relay.use_case.ExportCSVInputData;

import java.util.Map;

public class ShowCourseController {
    final ShowCourseInputBoundary  showCourseInteractor;

    public ShowCourseController(ShowCourseInputBoundary showCourseInteractor) {
        this.showCourseInteractor = showCourseInteractor;
    }

    public ResponseEntity<Map<String, Object>> execute(ShowCourseInputData inputData) {
            return showCourseInteractor.execute(inputData);
        }

    }
