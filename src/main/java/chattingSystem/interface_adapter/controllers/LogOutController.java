package chattingSystem.interface_adapter.controllers;

import chattingSystem.use_cases.log_out.LogOutInputBoundary;
import chattingSystem.use_cases.log_out.LogOutInputData;

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
