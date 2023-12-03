package relay.interface_adapter.show_courses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import relay.interface_adapter.ResponseFactory;
import relay.use_case.show_courses.ShowCourseOutputBoundary;
import relay.use_case.show_courses.ShowCourseOutputData;

import java.util.List;
import java.util.Map;

public class ShowCoursePresenter implements ShowCourseOutputBoundary {

    private final ShowCourseViewModel viewModel;

    public ShowCoursePresenter(ShowCourseViewModel viewModel) {

        this.viewModel = viewModel;
    }


    // Method from the ShowCourseOutputBoundary interface
    @Override
    public ResponseEntity<Map<String, Object>> prepareSuccessResponce(ShowCourseOutputData outputData) {
        // Extract relevant data from the output and update the ViewModel
        ShowCourseState showCourseState = (ShowCourseState) viewModel.getState();
        List<String> courseNames = outputData.getCourses();
        showCourseState.setCourses(courseNames);
        return ResponseFactory.createSuccessResponseEntity(viewModel);
    }


    @Override
    public ResponseEntity<Map<String, Object>> prepareFailResponce (ShowCourseOutputData failureShowCourseOutputData) {
        ShowCourseState showCourseState = (ShowCourseState) viewModel.getState();
        showCourseState.setErrorMessage(failureShowCourseOutputData.getErrorMessage());
        showCourseState.setStatusCode(HttpStatus.CONFLICT);
        return ResponseFactory.createFailureResponseEntity(viewModel);
    }

}

