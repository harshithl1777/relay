package relay.use_case.show_courses;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ShowCourseInputBoundary {
    ResponseEntity<Map<String, Object>> execute(ShowCourseInputData showCourseInputData);
}
