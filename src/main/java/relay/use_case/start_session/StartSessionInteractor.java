package relay.use_case.start_session;

import org.springframework.http.ResponseEntity;
import relay.entity.Session;
import relay.entity.SessionFactory;
import java.util.Map;

public class StartSessionInteractor implements StartSessionInputBoundary {
	final StartSessionDataAccessInterface sessionDataAccessObject;
	final StartSessionOutputBoundary sessionPresenter;
	final SessionFactory sessionFactory;

	public StartSessionInteractor(StartSessionDataAccessInterface sessionDataAccessInterface,
			StartSessionOutputBoundary sessionPresenter, SessionFactory sessionFactory) {
		this.sessionDataAccessObject = sessionDataAccessInterface;
		this.sessionPresenter = sessionPresenter;
		this.sessionFactory = sessionFactory;
	}

	public ResponseEntity<Map<String, Object>> execute(StartSessionInputData inputData) {
		try {
			Session newSession = sessionFactory.createSession(inputData.getCourseID(), inputData.getInstructorID());
			sessionDataAccessObject.save(newSession);

			StartSessionOutputData startSessionSuccessOutputData = new StartSessionOutputData(newSession.getCourseID(),
					newSession.getInstructorID(), newSession.getStartedAt(), newSession.getAlphaNumericCode(),
					newSession.getSessionID(), newSession.getAttendance());
			return sessionPresenter.prepareSuccessResponse(startSessionSuccessOutputData);
		} catch (Exception e) {
			StartSessionOutputData startSessionFailureOutputData = new StartSessionOutputData(e.getMessage());
			return sessionPresenter.prepareFailResponse(startSessionFailureOutputData);
		}
	}
}
