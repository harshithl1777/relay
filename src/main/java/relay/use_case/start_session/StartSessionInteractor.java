package relay.use_case.start_session;

import org.springframework.http.ResponseEntity;
import relay.entity.Session;
import relay.entity.SessionFactory;
import relay.exceptions.ResourceNotFoundException;

import java.util.Map;

public class StartSessionInteractor implements StartSessionInputBoundary {
	final StartSessionSessionDataAccessInterface sessionDataAccessObject;
	final StartSessionCourseDataAccessInterface courseDataAccessObject;
	final StartSessionInstructorDataAccessInterface instructorDataAccessObject;
	final StartSessionOutputBoundary sessionPresenter;
	final SessionFactory sessionFactory;

	public StartSessionInteractor(StartSessionSessionDataAccessInterface sessionDataAccessObject,
			StartSessionCourseDataAccessInterface courseDataAccessObject,
			StartSessionInstructorDataAccessInterface instructorDataAccessObject,
			StartSessionOutputBoundary sessionPresenter, SessionFactory sessionFactory) {
		this.sessionDataAccessObject = sessionDataAccessObject;
		this.courseDataAccessObject = courseDataAccessObject;
		this.instructorDataAccessObject = instructorDataAccessObject;
		this.sessionPresenter = sessionPresenter;
		this.sessionFactory = sessionFactory;
	}

	public ResponseEntity<Map<String, Object>> execute(StartSessionInputData inputData) {
		try {
			String courseID = inputData.getCourseID();
			String instructorID = inputData.getInstructorID();

			if (!courseDataAccessObject.exists(courseID) || !instructorDataAccessObject.exists(instructorID))
				throw new ResourceNotFoundException("No such course or instructor document exists.");

			Session newSession = sessionFactory.createSession(courseID, instructorID);
			sessionDataAccessObject.save(newSession);
			newSession.generateQRCode();
			sessionDataAccessObject.uploadImageToFirebaseStorage(newSession);

			StartSessionOutputData startSessionSuccessOutputData = new StartSessionOutputData(
					newSession.getCourseID(),
					newSession.getInstructorID(), newSession.getStartedAt(), newSession.getAlphaNumericCode(),
					newSession.getSessionID(), newSession.getAttendance());
			return sessionPresenter.prepareSuccessResponse(startSessionSuccessOutputData);
		} catch (ResourceNotFoundException e) {
			StartSessionOutputData startSessionFailureOutputData = new StartSessionOutputData(e.getMessage());
			return sessionPresenter.prepareFailResponse(startSessionFailureOutputData);
		}
	}
}
