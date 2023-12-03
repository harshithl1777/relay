package relay.use_case.create_course;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CreateCourseOutputBoundary {
    ResponseEntity<Map<String, Object>> prepareSuccessResponse(CreateCourseOutputData successCreateCourseOutputData);

	ResponseEntity<Map<String, Object>> prepareFailResponse(CreateCourseOutputData failureCreateCourseOutputData);
}
