package relay.use_case;

import relay.entity.AttendanceRecord;

import java.util.ArrayList;

public interface ExportCSVOutputBoundary {
        void prepareSuccessView(ExportCSVOutputData outputData);

        void prepareFailView(String error);


}
