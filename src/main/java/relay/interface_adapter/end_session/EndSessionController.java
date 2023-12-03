package relay.interface_adapter.end_session;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import relay.use_case.end_session.EndSessionInputBoundary;
import relay.use_case.end_session.EndSessionInputData;

import java.util.Map;

public class EndSessionController {
    final EndSessionInputBoundary endSessionUseCaseInteractor;

    public EndSessionController(EndSessionInputBoundary endSessionUseCaseInteractor){
        this.endSessionUseCaseInteractor = endSessionUseCaseInteractor;
    }

    public ResponseEntity<HttpStatus> execute(EndSessionInputData endSessionInputData) {
        return endSessionUseCaseInteractor.execute(endSessionInputData);
    }
}
