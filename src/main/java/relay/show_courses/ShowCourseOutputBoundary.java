package relay.show_courses;

import relay.use_case.ExportCSVOutputData;

public interface ShowCourseOutputBoundary {
    void prepareSuccessView(ShowCourseOutputData outputData);

    void prepareFailView(String error);



}
