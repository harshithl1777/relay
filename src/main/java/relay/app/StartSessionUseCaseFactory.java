package relay.app;

import relay.entity.SessionFactory;
import relay.interface_adapter.start_session.StartSessionController;
import relay.interface_adapter.start_session.StartSessionPresenter;
import relay.interface_adapter.start_session.StartSessionViewModel;
import relay.use_case.start_session.StartSessionSessionDataAccessInterface;
import relay.use_case.start_session.StartSessionCourseDataAccessInterface;
import relay.use_case.start_session.StartSessionInputBoundary;
import relay.use_case.start_session.StartSessionInstructorDataAccessInterface;
import relay.use_case.start_session.StartSessionInteractor;
import relay.use_case.start_session.StartSessionOutputBoundary;

public class StartSessionUseCaseFactory {
	private StartSessionUseCaseFactory() {
	}

	public static StartSessionController createStartSessionUseCase(StartSessionViewModel startSessionViewModel,
			StartSessionSessionDataAccessInterface startSessionSessionDataAccessInterface,
			StartSessionCourseDataAccessInterface startSessionCourseDataAccessInterface,
			StartSessionInstructorDataAccessInterface startSessionInstructorDataAccessInterface) {
		StartSessionOutputBoundary startSessionPresenter = new StartSessionPresenter(startSessionViewModel);
		SessionFactory sessionFactory = new SessionFactory();
		StartSessionInputBoundary startSessionInteractor = new StartSessionInteractor(
				startSessionSessionDataAccessInterface, startSessionCourseDataAccessInterface,
				startSessionInstructorDataAccessInterface, startSessionPresenter, sessionFactory);
		return new StartSessionController(startSessionInteractor);
	}
}
