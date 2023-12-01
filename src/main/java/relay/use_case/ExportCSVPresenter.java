package relay.use_case;

public class ExportCSVPresenter implements ExportCSVOutputBoundary {
    private ExportCSVView view;

    public ExportCSVPresenter(ExportCSVView view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(ExportCSVOutputData outputData) {
        // Format outputData and create a view model
        ExportCSVViewModel viewModel = new ExportCSVViewModel();

        // Display the result in the UI
        view.displayResult(viewModel);
    }

    @Override
    public void prepareFailView(String error) {
        // Handle and display the error in the UI
        view.displayError(error);
    }
}