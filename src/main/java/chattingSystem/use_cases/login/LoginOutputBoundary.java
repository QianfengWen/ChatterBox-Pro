package chattingSystem.use_cases.login;

import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData user, LogOutDataAccessBoundary logOutDataAccessBoundary);

    void prepareFailView(String error);
}
