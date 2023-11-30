package chattingSystem.use_cases.fun_joke;


public interface FunJokeOutputBoundry {
    void prepareSuccessView(FunJokeOutputData funJokeOutputData);
    void prepareFailView(String errorMessage);
}
