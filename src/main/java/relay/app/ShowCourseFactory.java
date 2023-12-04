package relay.app;

import relay.interface_adapter.show_courses.ShowCourseController;
import relay.interface_adapter.show_courses.ShowCoursePresenter;
import relay.interface_adapter.show_courses.ShowCourseViewModel;
import relay.use_case.show_courses.ShowCourseCourseDataAccessInterface;
import relay.use_case.show_courses.ShowCourseInputBoundary;
import relay.use_case.show_courses.ShowCourseInstructorDataAccessInterface;
import relay.use_case.show_courses.ShowCourseInteractor;
import relay.use_case.show_courses.ShowCourseOutputBoundary;

public class ShowCourseFactory {
	private ShowCourseFactory() {
	}

	public static ShowCourseController createShowCourseUseCase(ShowCourseViewModel showCourseViewModel,
			ShowCourseCourseDataAccessInterface showCourseCourseDataAccessInterface,
			ShowCourseInstructorDataAccessInterface showCourseInstructorDataAccessInterface) {
		ShowCourseOutputBoundary showCoursesPresenter = new ShowCoursePresenter(showCourseViewModel);
		ShowCourseInputBoundary showCourseInteractor = new ShowCourseInteractor(showCourseCourseDataAccessInterface,
				showCourseInstructorDataAccessInterface,
				showCoursesPresenter);
		return new ShowCourseController(showCourseInteractor);
	}
}
