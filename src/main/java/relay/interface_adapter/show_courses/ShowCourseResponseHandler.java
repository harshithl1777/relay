package relay.interface_adapter.show_courses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import relay.data_access.FirebaseCourseDataAccessObject;
import relay.data_access.FirebaseInstructorDataAccessObject;
import relay.use_case.show_courses.ShowCourseCourseDataAccessInterface;
import relay.app.ShowCourseFactory;
import relay.use_case.show_courses.ShowCourseInputData;
import relay.use_case.show_courses.ShowCourseInstructorDataAccessInterface;

import java.util.Objects;
import java.util.stream.Stream;

@RestController
public class ShowCourseResponseHandler {

	@GetMapping("/api/instructors/{id}/courses")
	public ResponseEntity<?> showCourses(@PathVariable String id) {
		if (!Stream.of(id).allMatch(Objects::nonNull)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}

		ShowCourseViewModel showCourseViewModel = new ShowCourseViewModel();

		ShowCourseCourseDataAccessInterface courseDataAccessObject = new FirebaseCourseDataAccessObject();
		ShowCourseInstructorDataAccessInterface instructorDataAccessObject = new FirebaseInstructorDataAccessObject();

		ShowCourseController showCourseController = ShowCourseFactory.createShowCourseUseCase(showCourseViewModel,
				courseDataAccessObject, instructorDataAccessObject);
		ShowCourseInputData requestInputData = new ShowCourseInputData(id);
		return showCourseController.execute(requestInputData);
	}
}
