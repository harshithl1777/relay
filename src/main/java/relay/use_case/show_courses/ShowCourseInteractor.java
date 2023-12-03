package relay.use_case.show_courses;

import org.springframework.http.ResponseEntity;
import relay.entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowCourseInteractor implements ShowCourseInputBoundary {
    final ShowCourseDataAccessInterface showCourseDataAccessInterface;
    final ShowCourseOutputBoundary outputBoundary;



    public ShowCourseInteractor(ShowCourseDataAccessInterface showCourseDataAccessInterface, ShowCourseOutputBoundary outputBoundary) {
        this.showCourseDataAccessInterface = showCourseDataAccessInterface;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public ResponseEntity<Map<String, Object>> execute(ShowCourseInputData showCourseInputData) {
        try {
            List<Course> courses;
            courses = showCourseDataAccessInterface.getCoursesByInstructor(showCourseInputData.getInstructorID());
            List<String> courseNames = new ArrayList<>();
            for (Course course: courses) {
                String courseName = course.getCourseName();
                courseNames.add(courseName);
            }

            ShowCourseOutputData outputData = new ShowCourseOutputData(courseNames);
            return outputBoundary.prepareSuccessResponce(outputData);

        }  catch (Exception e) {
            ShowCourseOutputData showCourseOutputData = new ShowCourseOutputData(e.getMessage());
            return outputBoundary.prepareFailResponce(showCourseOutputData);

        }

    }
}
