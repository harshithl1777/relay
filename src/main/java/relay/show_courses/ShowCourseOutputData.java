package relay.show_courses;

import relay.entity.AttendanceRecord;

import java.util.List;

public class ShowCourseOutputData {
    private final List<String> courseNames;

    public ShowCourseOutputData(List<String> courseNames) {
        this.courseNames = courseNames;
    }
}
