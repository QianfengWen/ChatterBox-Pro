package main.java.chattingSystem.interface_adapter.controllers;

import main.java.chattingSystem.use_cases.log_out.LogOutInputBoundary;
import main.java.chattingSystem.use_cases.log_out.LogOutInputData;

public class LogOutController {
    private final LogOutInputBoundary logOutInteractor;

    public LogOutController(LogOutInputBoundary logOutInteractor) {
        this.logOutInteractor = logOutInteractor;
    }

    public void execute(String username){
        LogOutInputData logOutInputData = new LogOutInputData(username);
        logOutInteractor.execute(logOutInputData);
    }
}
