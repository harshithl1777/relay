package relay.interface_adapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseFactory {
	public static ResponseEntity<Map<String, Object>> createSuccessResponseEntity(ViewModel viewModel) {
		State state = viewModel.getState();
		Gson gson = new Gson();
		String viewModelJSON = gson.toJson(viewModel);

		Map<String, Object> responseBody = gson.fromJson(viewModelJSON, new TypeToken<Map<String, Object>>() {
		}.getType());
		Map<String, Object> responsePayload = (Map<String, Object>) responseBody.remove("state");
		responsePayload.remove("errorMessage");
		responsePayload.remove("statusCode");

		return new ResponseEntity<>(responsePayload, state.getStatusCode());
	}

	public static ResponseEntity<Map<String, Object>> createFailureResponseEntity(ViewModel viewModel) {
		State state = viewModel.getState();
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("message", state.getErrorMessage());
		return new ResponseEntity<>(responseBody, state.getStatusCode());
	}
}
