package relay.use_case.log_attendance;

import relay.entity.Session;
import relay.exceptions.ResourceNotFoundException;

public interface LogAttendanceSessionDataAccessInterface {
    void save(Session session) throws ResourceNotFoundException;
    Session read (String sessionID) throws ResourceNotFoundException;

}
