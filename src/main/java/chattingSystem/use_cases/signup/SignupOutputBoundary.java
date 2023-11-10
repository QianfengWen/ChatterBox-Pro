package main.java.chattingSystem.use_cases.signup;

public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData user);

    void prepareNameFailView(String error);

    void prepareRepeatPWFailView(String error);

    void prepareInvalidPWFailView(String error);
}
