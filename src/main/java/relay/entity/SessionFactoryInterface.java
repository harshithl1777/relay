package relay.entity;

import java.util.Map;

public interface SessionFactoryInterface {
	Session createSession(String courseID, String instructorID);

	Session createSessionFromMap(Map<String, Object> sessionMap);
}
