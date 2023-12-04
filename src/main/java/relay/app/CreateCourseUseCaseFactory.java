package relay.app;

import relay.interface_adapter.create_course.CreateCourseViewModel;
import relay.interface_adapter.create_course.CreateCourseController;
import relay.interface_adapter.create_course.CreateCoursePresenter;
import relay.use_case.create_course.CreateCourseCourseDataAccessInterface;
import relay.use_case.create_course.CreateCourseInputBoundary;
import relay.use_case.create_course.CreateCourseInteractor;
import relay.use_case.create_course.CreateCourseOutputBoundary;

public class CreateCourseUseCaseFactory {
	private CreateCourseUseCaseFactory() {
	}

	public static CreateCourseController createCreateCourseUseCase(CreateCourseViewModel createCourseViewModel,
			CreateCourseCourseDataAccessInterface createCourseDataAccessInterface) {
		CreateCourseOutputBoundary createCoursePresenter = new CreateCoursePresenter(createCourseViewModel);
		CreateCourseInputBoundary createCourseInteractor = new CreateCourseInteractor(createCourseDataAccessInterface,
				createCoursePresenter);
		return new CreateCourseController(createCourseInteractor);
	}
}
