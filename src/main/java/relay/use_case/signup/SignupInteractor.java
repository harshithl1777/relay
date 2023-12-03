package relay.use_case.signup;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import relay.entity.Instructor;
import relay.exceptions.ResourceAlreadyExistsException;

public class SignupInteractor implements SignupInputBoundary {
	final SignupInstructorDataAccessInterface instructorDataAccessObject;
	final SignupOutputBoundary signupPresenter;

	public SignupInteractor(SignupInstructorDataAccessInterface instructorDataAccessObject,
			SignupOutputBoundary signupPresenter) {
		this.instructorDataAccessObject = instructorDataAccessObject;
		this.signupPresenter = signupPresenter;
	}

	@Override
	public ResponseEntity<Map<String, Object>> execute(SignupInputData signupInputData) {
		try {
			Instructor newInstructor = new Instructor(signupInputData.getFirstName(), signupInputData.getLastName(),
					signupInputData.getEmailAddress());
			instructorDataAccessObject.save(newInstructor);

			SignupOutputData signupSuccessOutputData = new SignupOutputData(newInstructor.getInstructorID(),
					newInstructor.getFirstName(), newInstructor.getLastName(), newInstructor.getEmailAddress());
			return signupPresenter.prepareSuccessResponse(signupSuccessOutputData);
		} catch (ResourceAlreadyExistsException e) {
			SignupOutputData signupFailureOutputData = new SignupOutputData(e.getMessage());
			return signupPresenter.prepareFailResponse(signupFailureOutputData);
		}
	}
}
