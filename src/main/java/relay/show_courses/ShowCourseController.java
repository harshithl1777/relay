package relay.show_courses;

import relay.use_case.ExportCSVInputBoundary;
import relay.use_case.ExportCSVInputData;

public class ShowCourseController {
    final ShowCourseInputBoundary  showCourseInteractor;

    public ShowCourseController(ShowCourseInputBoundary showCourseInteractor) {
        this.showCourseInteractor = showCourseInteractor;
    }

    public void execute(ShowCourseInputData inputData) {
        showCourseInteractor.execute(inputData);

    }
}
