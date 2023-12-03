package relay.show_courses;

import org.springframework.http.ResponseEntity;
import relay.use_case.ExportCSVInputData;

import java.util.Map;

public interface ShowCourseInputBoundary {
    ResponseEntity<Map<String, Object>> execute(ShowCourseInputData showCourseInputData);
}
