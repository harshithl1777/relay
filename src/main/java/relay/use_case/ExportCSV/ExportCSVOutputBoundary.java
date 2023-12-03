package relay.use_case.ExportCSV;

public interface ExportCSVOutputBoundary {
        void prepareSuccessView(ExportCSVOutputData outputData);

        void prepareFailView(String error);


}
