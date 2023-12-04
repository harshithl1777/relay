package relay.use_case.log_attendance;

import relay.entity.AttendanceRecord;
import relay.entity.Session;
import relay.exceptions.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class InMemorySessionDataAccessObject implements LogAttendanceSessionDataAccessInterface {
    private final Map<String, Session> sessionData = new HashMap<>();


    @Override
    public void save(Session session) throws ResourceNotFoundException {
        sessionData.put(session.getSessionID(), session);
    }

    @Override
    public Session read(String sessionID) throws ResourceNotFoundException {
        if (sessionData.containsKey(sessionID)) {
            return sessionData.get(sessionID);
        } else {
            throw new ResourceNotFoundException("No such session exists");
        }
    }
}
