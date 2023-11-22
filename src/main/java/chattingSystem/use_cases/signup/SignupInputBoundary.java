package chattingSystem.use_cases.signup;


import java.io.IOException;

public interface SignupInputBoundary {
    void execute(SignupInputData signupInputData) throws IOException;

}
