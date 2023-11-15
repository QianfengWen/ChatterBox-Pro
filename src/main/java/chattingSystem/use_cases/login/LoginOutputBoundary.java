package main.java.chattingSystem.use_cases.login;

import main.java.chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData user, LogOutDataAccessBoundary logOutDataAccessBoundary);

    void prepareFailView(String error);
}
