package relay.interface_adapter.create_course;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import relay.interface_adapter.ResponseFactory;
import relay.use_case.create_course.CreateCourseOutputBoundary;
import relay.use_case.create_course.CreateCourseOutputData;

public class CreateCoursePresenter implements CreateCourseOutputBoundary {
	private final CreateCourseViewModel createCourseViewModel;

	public CreateCoursePresenter(CreateCourseViewModel createCourseViewModel) {
		this.createCourseViewModel = createCourseViewModel;
	}

	@Override
	public ResponseEntity<Map<String, Object>> prepareSuccessResponse(CreateCourseOutputData successCreateCourseOutputData) {
		CreateCourseState createCourseState = (CreateCourseState) createCourseViewModel.getState();
		createCourseState.setCourseID(successCreateCourseOutputData.getCourseID());
		createCourseState.setCourseName(successCreateCourseOutputData.getCourseName());
		createCourseState.setInstructorID(successCreateCourseOutputData.getInstructorID());
		createCourseState.setHistory(successCreateCourseOutputData.getHistory());
		createCourseState.setStatusCode(HttpStatus.CREATED);
		return ResponseFactory.createSuccessResponseEntity(createCourseViewModel);
	}

	@Override
	public ResponseEntity<Map<String, Object>> prepareFailResponse(CreateCourseOutputData failureCreateCourseOutputData) {
		CreateCourseState createCourseState = (CreateCourseState) createCourseViewModel.getState();
		createCourseState.setErrorMessage(failureCreateCourseOutputData.getErrorMessage());
		createCourseState.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseFactory.createFailureResponseEntity(createCourseViewModel);
	}

}
