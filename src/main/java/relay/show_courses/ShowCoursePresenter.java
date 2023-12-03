package relay.show_courses;

import java.util.List;

public class ShowCoursePresenter implements ShowCourseOutputBoundary {

    private final ShowCourseViewModel viewModel;

    public ShowCoursePresenter(ShowCourseViewModel viewModel) {

        this.viewModel = viewModel;
    }



    // Method from the ShowCourseOutputBoundary interface
    @Override
    public void prepareSuccessView(ShowCourseOutputData outputData) {
        // Extract relevant data from the output and update the ViewModel
        List<String> courseNames = outputData.getCourseNames();
        viewModel.setState(new ShowCourseState(courseNames));
    }

    // Method from the ShowCourseOutputBoundary interface
    @Override
    public void prepareFailView(String error) {
        // Handle the error presentation (optional)
        // You might want to log the error, show an error message to the user, etc.
        System.err.println("Error: " + error);
    }
}

