package relay.use_case.end_session;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import relay.entity.AttendanceRecord;
import relay.entity.Course;
import relay.entity.Session;

import java.util.List;

public class EndSessionInteractor implements EndSessionInputBoundary {
	final EndSessionSessionDataAccessInterface sessionDataAccessObject;

	final EndSessionCourseDataAccessInterface courseDataAccessObject;

	final EndSessionOutputBoundary sessionPresenter;

	public EndSessionInteractor(EndSessionSessionDataAccessInterface sessionDataAccessObject,
			EndSessionCourseDataAccessInterface courseDataAccessObject,
			EndSessionOutputBoundary sessionPresenter) {
		this.sessionDataAccessObject = sessionDataAccessObject;
		this.courseDataAccessObject = courseDataAccessObject;
		this.sessionPresenter = sessionPresenter;
	}

	@Override
	public ResponseEntity<HttpStatus> execute(EndSessionInputData inputData) {
		try {
			String sessionID = inputData.getSessionID();
			Session session = sessionDataAccessObject.read(sessionID);
			List<AttendanceRecord> attendance = session.getAttendance();

			Course course = courseDataAccessObject.getCourseByID(session.getCourseID());
			course.appendHistory(attendance);
			courseDataAccessObject.save(course);

			sessionDataAccessObject.delete(sessionID);
			sessionDataAccessObject.deleteFile(sessionID);

			EndSessionOutputData endSessionSuccessOutputData = new EndSessionOutputData();

			return sessionPresenter.prepareSuccessResponse(endSessionSuccessOutputData);
		} catch (Exception e) {
			EndSessionOutputData endSessionFailureOutputData = new EndSessionOutputData(e.getMessage());
			return sessionPresenter.prepareFailResponse(endSessionFailureOutputData);
		}
	}
}
