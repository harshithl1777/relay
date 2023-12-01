package relay.use_case;

public interface ExportCSVView {
        void displayResult(ExportCSVViewModel viewModel);
        void displayError(String errorMessage);
}
