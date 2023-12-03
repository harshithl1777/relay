package relay.app;

import relay.interface_adapter.login.LoginController;
import relay.interface_adapter.login.LoginPresenter;
import relay.interface_adapter.login.LoginViewModel;
import relay.use_case.login.LoginInputBoundary;
import relay.use_case.login.LoginInstructorDataAccessInterface;
import relay.use_case.login.LoginInteractor;
import relay.use_case.login.LoginOutputBoundary;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory() {
    }

    public static LoginController createLoginUseCase(LoginViewModel loginViewModel,
                                                       LoginInstructorDataAccessInterface loginInstructorDataAccessInterface) {
        LoginOutputBoundary loginPresenter = new LoginPresenter(loginViewModel);
        LoginInputBoundary loginInteractor = new LoginInteractor(loginInstructorDataAccessInterface,
                loginPresenter);
        return new LoginController(loginInteractor);
    }
}
