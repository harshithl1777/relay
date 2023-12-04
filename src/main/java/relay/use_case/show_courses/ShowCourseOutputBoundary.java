package relay.use_case.show_courses;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ShowCourseOutputBoundary {
    ResponseEntity<Map<String, Object>> prepareSuccessResponse(ShowCourseOutputData outputData);

    ResponseEntity<Map<String, Object>> prepareFailResponse(ShowCourseOutputData failureShowCourseOutputData);



}
