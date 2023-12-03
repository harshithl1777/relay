package relay.use_case.create_course;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CreateCourseInputBoundary {
    ResponseEntity<Map<String, Object>> execute(CreateCourseInputData createCourseInputData);
}
