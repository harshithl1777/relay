package relay.show_courses;

import org.springframework.http.ResponseEntity;
import relay.use_case.ExportCSVOutputData;

import java.util.Map;

public interface ShowCourseOutputBoundary {
    ResponseEntity<Map<String, Object>> prepareSuccessResponce(ShowCourseOutputData outputData);

    ResponseEntity<Map<String, Object>> prepareFailResponce(ShowCourseOutputData failureShowCourseOutputData);



}
