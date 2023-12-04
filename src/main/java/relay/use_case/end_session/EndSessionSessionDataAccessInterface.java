package relay.use_case.end_session;

import relay.entity.Session;
import relay.exceptions.ResourceNotFoundException;

public interface EndSessionSessionDataAccessInterface {
	void save(Session session) throws ResourceNotFoundException;

	void delete(String sessionID) throws ResourceNotFoundException;

	void deleteFile(String sessionID) throws ResourceNotFoundException;

	Session read(String sessionID) throws ResourceNotFoundException;
}
