package relay.use_case.signup;

public interface SignupOutputBoundary {
	void prepareSuccessResponse(SignupOutputData user);

	void prepareFailResponse(String error);
}
