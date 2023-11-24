package chattingSystem.use_cases.log_out;

public interface LogOutOutputBoundary {
    void prepareSuccessView(LogOutOutputData logOutOutputData);
    void prepareFailView(LogOutOutputData logOutOutputData);
}
