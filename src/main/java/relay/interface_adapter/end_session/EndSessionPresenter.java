package relay.interface_adapter.end_session;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import relay.interface_adapter.ResponseFactory;
import relay.use_case.end_session.EndSessionOutputBoundary;
import relay.use_case.end_session.EndSessionOutputData;

import java.util.Map;

/**
 * Presenter class for handling the results of the end session use case.
 * This class updates the end session view model based on the success or failure output data.
 */
public class EndSessionPresenter implements EndSessionOutputBoundary {

    private final EndSessionViewModel endSessionViewModel;

    /**
     * Constructs a new instance of EndSessionPresenter with the provided end session view model.
     *
     * @param endSessionViewModel The view model for the end session operation.
     */
    public EndSessionPresenter(EndSessionViewModel endSessionViewModel) {
        this.endSessionViewModel = endSessionViewModel;
    }

    /**
     * Prepares a success response for the end session use case.
     *
     * @param endSessionOutputData The output data containing information for the successful end session operation.
     * @return A ResponseEntity containing a map of key-value pairs representing the success response.
     */
    @Override
    public ResponseEntity<HttpStatus> prepareSuccessResponse(EndSessionOutputData endSessionOutputData) {
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }


    /**
     * Prepares a failure response for the login use case.
     *
     * @param endSessionOutputData The output data containing information for the failed login operation.
     * @return A ResponseEntity containing a map of key-value pairs representing the failure response.
     */
    @Override
    public ResponseEntity<HttpStatus> prepareFailResponse(EndSessionOutputData endSessionOutputData) {
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}
