package relay.show_courses;

import relay.entity.Course;
import relay.use_case.ExportCSVOutputData;

import java.util.ArrayList;
import java.util.List;

public class ShowCourseInteractor implements ShowCourseInputBoundary {
    final FirebaseCourseDataObjectInterface firebaseCourseDataObjectInterface;
    final ShowCourseOutputBoundary outputBoundary;



    public ShowCourseInteractor(FirebaseCourseDataObjectInterface firebaseCourseDataObjectInterface, ShowCourseOutputBoundary outputBoundary) {
        this.firebaseCourseDataObjectInterface = firebaseCourseDataObjectInterface;
        this.outputBoundary = outputBoundary;
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
            String errorMessage = "Error getting courses: " + e.getMessage();
            outputBoundary.prepareFailView(errorMessage);

        }

    }
}
