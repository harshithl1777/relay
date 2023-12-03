package relay.use_case.start_session;

import relay.entity.Session;

public interface StartSessionDataAccessInterface {
    void save(Session session) throws Exception;
}
