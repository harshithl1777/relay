package relay.use_case.show_courses;

import java.util.List;

import org.springframework.http.HttpStatus;

import relay.entity.Course;

public class ShowCourseOutputData {

	private List<Course> courses;
	private String errorMessage;
	private HttpStatus statusCode;
	private boolean useCaseSuccess;

	public ShowCourseOutputData(List<Course> courses) {
		this.courses = courses;
		this.useCaseSuccess = true;
	}

	public ShowCourseOutputData(String errorMessage) {
		this.useCaseSuccess = false;
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public boolean getUseCaseSuccess() {
		return useCaseSuccess;
	}

}
