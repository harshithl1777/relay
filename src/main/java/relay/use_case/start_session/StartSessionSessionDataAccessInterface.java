package relay.use_case.start_session;

import relay.entity.Session;

public interface StartSessionSessionDataAccessInterface {
	void save(Session session);
}
