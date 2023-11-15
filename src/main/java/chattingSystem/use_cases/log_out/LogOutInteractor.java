package main.java.chattingSystem.use_cases.log_out;


import main.java.chattingSystem.entities.User.User;

public class LogOutInteractor implements LogOutInputBoundary {
    private LogOutOutputBoundary logOutOutputBoundary;
    private LogOutDataAccessBoundary logOutDataAccessBoundary;

    public LogOutInteractor(LogOutOutputBoundary logOutOutputBoundary, LogOutDataAccessBoundary logOutDataAccessBoundary) {
        this.logOutOutputBoundary = logOutOutputBoundary;
        this.logOutDataAccessBoundary = logOutDataAccessBoundary;
    }

    @Override
    public void execute(LogOutInputData inputData) {
        String username = inputData.getUsername();
        logOutDataAccessBoundary.logOut(username);
        User userLoggedOut = logOutDataAccessBoundary.get(username);
        if (userLoggedOut.getIsOnline()) {
            logOutOutputBoundary.prepareFailView(new LogOutOutputData(username, false));
        } else {
            logOutOutputBoundary.prepareSuccessView(new LogOutOutputData(username, true));
        }
    }
}
