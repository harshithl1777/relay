package relay.app;

import relay.interface_adapter.end_session.EndSessionController;
import relay.interface_adapter.end_session.EndSessionPresenter;
import relay.interface_adapter.end_session.EndSessionViewModel;
import relay.use_case.end_session.EndSessionCourseDataAccessInterface;
import relay.use_case.end_session.EndSessionInputBoundary;
import relay.use_case.end_session.EndSessionInteractor;
import relay.use_case.end_session.EndSessionOutputBoundary;
import relay.use_case.end_session.EndSessionSessionDataAccessInterface;

public class EndSessionUseCaseFactory {
	private EndSessionUseCaseFactory() {
	}

	public static EndSessionController createEndSessionUseCase(EndSessionViewModel endSessionViewModel,
			EndSessionCourseDataAccessInterface endSessionCourseDataAccessInterface,
			EndSessionSessionDataAccessInterface endSessionSessionDataAccessInterface) {
		EndSessionOutputBoundary endSessionPresenter = new EndSessionPresenter(
				endSessionViewModel);
		EndSessionInputBoundary endSessionInteractor = new EndSessionInteractor(endSessionSessionDataAccessInterface,
				endSessionCourseDataAccessInterface, endSessionPresenter);
		return new EndSessionController(endSessionInteractor);
	}
}
