package relay.interface_adapter.start_session;

import org.springframework.http.ResponseEntity;
import relay.use_case.start_session.StartSessionInputBoundary;
import relay.use_case.start_session.StartSessionInputData;

import java.util.Map;

public class StartSessionController {
    final StartSessionInputBoundary startSessionUseCaseInteractor;

    public StartSessionController(StartSessionInputBoundary startSessionUseCaseInteractor){
        this.startSessionUseCaseInteractor = startSessionUseCaseInteractor;
    }

    public ResponseEntity<Map<String, Object>> execute(StartSessionInputData startSessionInputData) {
        return startSessionUseCaseInteractor.execute(startSessionInputData);
    }
}
