package relay.show_courses;

import relay.entity.Course;
import relay.use_case.ExportCSVOutputData;

import java.util.ArrayList;
import java.util.List;

public class ShowCourseInteractor implements ShowCourseInputBoundary {
    final FirebaseCourseDataObjectInterface firebaseCourseDataObjectInterface;
    final ShowCourseOutputBoundary outputBoundary;
    final FirebaseCourseDataObjectInterface showCourseDataAccessInterface;


    public ShowCourseInteractor(FirebaseCourseDataObjectInterface firebaseCourseDataObjectInterface, ShowCourseOutputBoundary outputBoundary, FirebaseCourseDataObjectInterface showCourseDataAccessInterface) {
        this.firebaseCourseDataObjectInterface = firebaseCourseDataObjectInterface;
        this.outputBoundary = outputBoundary;
        this.showCourseDataAccessInterface = showCourseDataAccessInterface;
    }

    @Override
    public void execute(ShowCourseInputData showCourseInputData) {
        try {
            List<Course> courses;
            courses = firebaseCourseDataObjectInterface.getCoursesByInstructor(showCourseInputData.getInstructorID());
            List<String> courseNames = new ArrayList<>();
            for (Course course: courses) {
                String courseName = course.getCourseName();
                courseNames.add(courseName);
            }

            ShowCourseOutputData outputData = new ShowCourseOutputData(courseNames);
            outputBoundary.prepareSuccessView(outputData);

        }  catch (Exception e) {
            String errorMessage = "Error exporting attendance records: " + e.getMessage();
            outputBoundary.prepareFailView(errorMessage);


        }

    }
}
