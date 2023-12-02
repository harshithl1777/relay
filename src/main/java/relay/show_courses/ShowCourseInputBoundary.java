package relay.show_courses;

import relay.use_case.ExportCSVInputData;

public interface ShowCourseInputBoundary {
    void execute(ShowCourseInputData showCourseInputData);
}
