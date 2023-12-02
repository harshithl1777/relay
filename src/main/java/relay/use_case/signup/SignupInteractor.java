package relay.use_case.signup;

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
	public void execute(SignupInputData signupInputData) {
		try {
			Instructor newInstructor = new Instructor(signupInputData.getFirstName(), signupInputData.getLastName(),
					signupInputData.getEmailAddress());
			instructorDataAccessObject.save(newInstructor);

			signupPresenter.prepareSuccessResponse(null);
		} catch (ResourceAlreadyExistsException e) {

		}
	}
}
