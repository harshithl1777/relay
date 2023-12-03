package relay.use_case.login;

public interface LoginOutputBoundary {
    void prepareSuccessResponse(LoginOutputData loginOutputData);

    void prepareFailResponse(LoginInputData loginInputData);


}
