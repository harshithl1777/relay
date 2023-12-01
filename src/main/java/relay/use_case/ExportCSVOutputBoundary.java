package relay.use_case;

public interface ExportCSVOutputBoundary {
        void prepareSuccessView(ExportCSVOutputData user);

        void prepareFailView(String error);


}
