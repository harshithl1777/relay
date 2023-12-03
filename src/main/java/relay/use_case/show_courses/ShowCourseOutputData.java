package relay.use_case.show_courses;

import relay.entity.AttendanceRecord;

import java.util.List;


import relay.interface_adapter.State;
import relay.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


import org.springframework.http.HttpStatus;
import relay.entity.AttendanceRecord;
import relay.interface_adapter.State;

import java.util.ArrayList;
import java.util.List;


public class ShowCourseOutputData {

    private List<String> courses;
    private String errorMessage;
    private HttpStatus statusCode;
    private boolean useCaseSuccess;

    public ShowCourseOutputData(List<String> courses) {
        this.courses = courses;
        this.useCaseSuccess = true;


    }

    public ShowCourseOutputData(String errorMessage) {
        this.useCaseSuccess = false;
        this.errorMessage = errorMessage;
    }



    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

   public List<String> getCourses() {
        return courses;
   }

}
