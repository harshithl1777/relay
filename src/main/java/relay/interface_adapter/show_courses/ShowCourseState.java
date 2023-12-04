package relay.interface_adapter.show_courses;

import org.springframework.http.HttpStatus;
import relay.entity.AttendanceRecord;
import relay.entity.Course;
import relay.interface_adapter.State;

import java.util.ArrayList;
import java.util.List;

public class ShowCourseState implements State {
	private List<Course> courses;
	private String errorMessage;
	private HttpStatus statusCode;

	public ShowCourseState(ShowCourseState copyState) {
		this.statusCode = copyState.statusCode;
		this.courses = copyState.courses;
	}

	public ShowCourseState() {
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Course> getCourses() {
		return courses;
	}
}
