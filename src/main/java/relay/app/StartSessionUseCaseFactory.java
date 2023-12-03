package relay.app;

import relay.entity.SessionFactory;
import relay.interface_adapter.start_session.StartSessionController;
import relay.interface_adapter.start_session.StartSessionPresenter;
import relay.interface_adapter.start_session.StartSessionViewModel;
import relay.use_case.start_session.StartSessionDataAccessInterface;
import relay.use_case.start_session.StartSessionInputBoundary;
import relay.use_case.start_session.StartSessionInteractor;
import relay.use_case.start_session.StartSessionOutputBoundary;

public class StartSessionUseCaseFactory {
	private StartSessionUseCaseFactory() {
	}

	public static StartSessionController createStartSessionUseCase(StartSessionViewModel startSessionViewModel,
			StartSessionDataAccessInterface startSessionDataAccessInterface) {
		StartSessionOutputBoundary startSessionPresenter = new StartSessionPresenter(startSessionViewModel);
		SessionFactory sessionFactory = new SessionFactory();
		StartSessionInputBoundary startSessionInteractor = new StartSessionInteractor(
				startSessionDataAccessInterface, startSessionPresenter, sessionFactory);
		return new StartSessionController(startSessionInteractor);
	}
}
