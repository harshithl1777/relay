package relay.use_case.create_course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import relay.entity.Course;
import relay.exceptions.ResourceAlreadyExistsException;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCourseInteractorTest{
    CreateCourseCourseDataAccessInterface courseRepository;
    @BeforeEach
    void setupInstructorRepository() {
        this.courseRepository = new InMemoryCourseCourseDataAccessObject();
    }
    @Test
    void successTest() throws ResourceAlreadyExistsException {
        CreateCourseInputData createCourseInputData = new CreateCourseInputData("MAT137", "cr7siuuu");

        Course course = new Course("MAT137", "cr7siuu");
        course.setHistory(new ArrayList<>());

        ((InMemoryCourseCourseDataAccessObject) courseRepository).save(course);

        CreateCourseOutputBoundary successPresenter = new CreateCourseOutputBoundary() {
            @Override
            public ResponseEntity<Map<String, Object>> prepareSuccessResponse(CreateCourseOutputData createCourseOutputData) {
                assertEquals("MAT137", createCourseOutputData.getCourseName());
                assertEquals(new ArrayList<>(), createCourseOutputData.getHistory());
                assertEquals("cr7siuuu", createCourseOutputData.getInstructorID());
                return null;
            }

            @Override
            public ResponseEntity<Map<String, Object>> prepareFailResponse(CreateCourseOutputData createCourseOutputData) {
                throw new RuntimeException("Use Case Failure Not Expected");
            }

        };
        CreateCourseInputBoundary interactor = new CreateCourseInteractor(courseRepository, successPresenter);
        interactor.execute(createCourseInputData);

    }

    @Test
    void failure() {
        CreateCourseInputData createCourseInputData = new CreateCourseInputData("John Cena", "goat123");
        CreateCourseOutputBoundary successPresenter = new CreateCourseOutputBoundary() {
            @Override
            public ResponseEntity<Map<String, Object>> prepareSuccessResponse(CreateCourseOutputData createCourseOutputData) {
                throw new RuntimeException("Use Case Success Not Expected");
            }

            @Override
            public ResponseEntity<Map<String, Object>> prepareFailResponse(CreateCourseOutputData createCourseOutputData) {
                assert !createCourseOutputData.getErrorMessage().isBlank();  // test if error message has been set
                return null;
            }
        };

        CreateCourseInputBoundary interactor = new CreateCourseInteractor(courseRepository, successPresenter);
        interactor.execute(createCourseInputData);
    }
}
