package relay.use_case;

import relay.entity.AttendanceRecord;

import java.util.ArrayList;

public interface ExportCSVDataAccessInterface {

    public ArrayList<AttendanceRecord> retreiveAttendanceRecords();

}
