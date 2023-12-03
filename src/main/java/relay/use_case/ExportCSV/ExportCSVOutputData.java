package relay.use_case.ExportCSV;

import relay.entity.AttendanceRecord;

import java.util.List;

public class ExportCSVOutputData {
    private List<AttendanceRecord> courseHistory;

    public ExportCSVOutputData(List<AttendanceRecord> courseHistory) {
        this.courseHistory = courseHistory;
    }

    public List<AttendanceRecord> getCourseHistory() {
        return courseHistory;
    }

    public void setCourseHistory(List<AttendanceRecord> courseHistory) {
        this.courseHistory = courseHistory;
    }
}