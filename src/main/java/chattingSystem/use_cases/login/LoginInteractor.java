package chattingSystem.use_cases.login;
import chattingSystem.entities.User.User;
import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;

public class LoginInteractor implements LoginInputBoundary{
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;
    private LogOutDataAccessBoundary logOutDataAccessBoundary;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary,
                            LogOutDataAccessBoundary logOutDataAccessBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.logOutDataAccessBoundary = logOutDataAccessBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else if (userDataAccessObject.get(username).getIsOnline()) {
            loginPresenter.prepareFailView(username + ": Account is already online.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.get(loginInputData.getUsername());
                //set user online
                user.setOnline(true);
                LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), false);
                loginPresenter.prepareSuccessView(loginOutputData, logOutDataAccessBoundary);
            }
        }

    }
}
