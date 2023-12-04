package relay.use_case.create_course;

import org.springframework.http.ResponseEntity;
import relay.entity.Course;

import java.util.Map;

public class CreateCourseInteractor implements CreateCourseInputBoundary {
	final CreateCourseCourseDataAccessInterface instructorDataAccessObject;
	final CreateCourseOutputBoundary createCoursePresenter;

	public CreateCourseInteractor(CreateCourseCourseDataAccessInterface instructorDataAccessObject,
                                  CreateCourseOutputBoundary createCoursePresenter) {
		this.instructorDataAccessObject = instructorDataAccessObject;
		this.createCoursePresenter = createCoursePresenter;
	}

	@Override
	public ResponseEntity<Map<String, Object>> execute(CreateCourseInputData createCourseInputData) {
		try {
			Course newCourse = new Course(createCourseInputData.getCourseName(), createCourseInputData.getInstructorID());
			instructorDataAccessObject.save(newCourse);

			CreateCourseOutputData createCourseSuccessOutputData = new CreateCourseOutputData(newCourse.getInstructorID(),
					newCourse.getCourseName(), newCourse.getCourseID(), newCourse.getHistory());
			return createCoursePresenter.prepareSuccessResponse(createCourseSuccessOutputData);
		} catch (Exception e) {
			CreateCourseOutputData createCourseFailureOutputData = new CreateCourseOutputData(e.getMessage());
			return createCoursePresenter.prepareFailResponse(createCourseFailureOutputData);
		}
	}
}
