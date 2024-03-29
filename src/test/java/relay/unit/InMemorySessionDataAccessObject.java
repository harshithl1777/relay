package relay.unit;

import relay.entity.Session;
import relay.exceptions.ResourceNotFoundException;
import relay.use_case.end_session.EndSessionSessionDataAccessInterface;
import relay.use_case.start_session.StartSessionSessionDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory Instructor Data Access Object for testing purposes.
 * This class provides methods for reading and saving instructor data in memory.
 */
public class InMemorySessionDataAccessObject implements StartSessionSessionDataAccessInterface, EndSessionSessionDataAccessInterface {

    // Storage for instructor data in memory
    private final Map<String, Session> sessions = new HashMap<>();
    private static int nextID = 0;

    /**
     * Retrieves a session  by their email address.
     *
     * @param sessionID The email address of the instructor to retrieve.
     * @return The instructor associated with the given email address.
     * @throws ResourceNotFoundException if no session is found with the provided sessionID.
     */
    public Session read(String sessionID) throws ResourceNotFoundException {
        if (!sessions.containsKey(sessionID)) {
            throw new ResourceNotFoundException("No such instructor exists");
        }
        return sessions.get(sessionID);

    }

    /**
     * Saves a session to the in-memory data storage.
     *
     * @param session The session to be saved.
     */
    @Override
    public void save(Session session) {
        if (session.getSessionID() != null && !sessions.containsKey(session.getSessionID())) {
            sessions.put(session.getSessionID(), session);
        } else {
            session.setSessionID(String.valueOf(nextID));
            session.setAlphaNumericCode("frcnr");
            sessions.put(String.valueOf(nextID++), session);
        }
    }

    @Override
    public void delete(String sessionID) throws ResourceNotFoundException {
        if (!sessions.containsKey(sessionID)) {
            throw new ResourceNotFoundException("Session does not exists");
        }
        sessions.remove(sessionID);
    }

    @Override
    public void deleteFile(String sessionID) throws ResourceNotFoundException {
        return;
    }

    @Override
    public void uploadImageToFirebaseStorage(Session session) {
        throw new UnsupportedOperationException("Unimplemented method 'uploadImageToFirebaseStorage'");
    }

}
