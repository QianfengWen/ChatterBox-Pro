package main.java.chattingSystem.interface_adapter.controllers;

import main.java.chattingSystem.use_cases.login.LoginInputBoundary;
import main.java.chattingSystem.use_cases.login.LoginInputData;

public class LoginController {
    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }


    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
