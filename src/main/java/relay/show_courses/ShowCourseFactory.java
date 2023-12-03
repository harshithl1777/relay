package relay.show_courses;

public class ShowCourseFactory {
    private ShowCourseFactory() {
    }

    public static ShowCourseController createShowCourseUseCase(ShowCourseViewModel showCourseViewModel,
                                                       FirebaseCourseDataObjectInterface firebaseCourseDataObjectInterface) {
        ShowCourseOutputBoundary showCoursesPresenter = new ShowCoursePresenter(showCourseViewModel);
        ShowCourseInputBoundary showCourseInteractor = new ShowCourseInteractor(firebaseCourseDataObjectInterface,
                showCoursesPresenter);
        return new ShowCourseController (showCourseInteractor);
    }
}