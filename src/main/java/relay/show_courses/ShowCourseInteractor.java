package relay.show_courses;

import org.springframework.http.ResponseEntity;
import relay.entity.Course;
import relay.use_case.ExportCSVOutputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowCourseInteractor implements ShowCourseInputBoundary {
    final FirebaseCourseDataObjectInterface firebaseCourseDataObjectInterface;
    final ShowCourseOutputBoundary outputBoundary;



    public ShowCourseInteractor(FirebaseCourseDataObjectInterface firebaseCourseDataObjectInterface, ShowCourseOutputBoundary outputBoundary) {
        this.firebaseCourseDataObjectInterface = firebaseCourseDataObjectInterface;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public ResponseEntity<Map<String, Object>> execute(ShowCourseInputData showCourseInputData) {
        try {
            List<Course> courses;
            courses = firebaseCourseDataObjectInterface.getCoursesByInstructor(showCourseInputData.getInstructorID());
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
