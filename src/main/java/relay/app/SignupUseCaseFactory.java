package relay.app;

import relay.interface_adapter.signup.SignupController;
import relay.interface_adapter.signup.SignupPresenter;
import relay.interface_adapter.signup.SignupViewModel;
import relay.use_case.signup.SignupInputBoundary;
import relay.use_case.signup.SignupInstructorDataAccessInterface;
import relay.use_case.signup.SignupInteractor;
import relay.use_case.signup.SignupOutputBoundary;

public class SignupUseCaseFactory {
	private SignupUseCaseFactory() {
	}

	public static SignupController createSignupUseCase(SignupViewModel signupViewModel,
			SignupInstructorDataAccessInterface signupInstructorDataAccessInterface) {
		SignupOutputBoundary signupPresenter = new SignupPresenter(signupViewModel);
		SignupInputBoundary signupInteractor = new SignupInteractor(signupInstructorDataAccessInterface,
				signupPresenter);
		return new SignupController(signupInteractor);
	}
}
