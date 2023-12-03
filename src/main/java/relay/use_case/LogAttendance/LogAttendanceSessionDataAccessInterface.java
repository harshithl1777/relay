package relay.use_case.LogAttendance;

import relay.entity.Session;
import relay.exceptions.ResourceNotFoundException;

public interface LogAttendanceSessionDataAccessInterface {
    void save(Session session) throws ResourceNotFoundException;
    Session read (String sessionID) throws ResourceNotFoundException;

}
