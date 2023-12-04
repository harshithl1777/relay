package relay.use_case.show_courses;

import org.springframework.http.ResponseEntity;
import relay.entity.Course;
import relay.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Map;

public class ShowCourseInteractor implements ShowCourseInputBoundary {
	final ShowCourseCourseDataAccessInterface showCourseCourseDataAccessObject;
	final ShowCourseInstructorDataAccessInterface showCourseInstructorDataAccessObject;
	final ShowCourseOutputBoundary outputBoundary;

	public ShowCourseInteractor(ShowCourseCourseDataAccessInterface showCourseCourseDataAccessObject,
			ShowCourseInstructorDataAccessInterface showCourseInstructorDataAccessObject,
			ShowCourseOutputBoundary outputBoundary) {
		this.showCourseCourseDataAccessObject = showCourseCourseDataAccessObject;
		this.showCourseInstructorDataAccessObject = showCourseInstructorDataAccessObject;
		this.outputBoundary = outputBoundary;
	}

	@Override
	public ResponseEntity<Map<String, Object>> execute(ShowCourseInputData showCourseInputData) {
		try {
			String instructorID = showCourseInputData.getInstructorID();
			if (!showCourseInstructorDataAccessObject.exists(instructorID)) {
				throw new ResourceNotFoundException("No such instructor document exists");
			}
			List<Course> courses = showCourseCourseDataAccessObject.getCoursesByInstructor(instructorID);

			ShowCourseOutputData outputData = new ShowCourseOutputData(courses);
			return outputBoundary.prepareSuccessResponse(outputData);

		} catch (ResourceNotFoundException e) {
			ShowCourseOutputData showCourseOutputData = new ShowCourseOutputData(e.getMessage());
			return outputBoundary.prepareFailResponse(showCourseOutputData);

		}

	}
}
