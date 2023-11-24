package chattingSystem.interface_adapter.state;

public class LoginState {
    private String username = "";
    private String usernameError = null;
    private boolean SignupSuccess = false;
    private String password = "";
    private String passwordError = null;
    private boolean loginSuccess = false;

    public LoginState(LoginState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
        SignupSuccess = copy.SignupSuccess;
        loginSuccess = copy.loginSuccess;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoginState() {}

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }
    public boolean SignupSuccess() {
        return SignupSuccess;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSignupSuccess(boolean SignupSuccess) {
        this.SignupSuccess = SignupSuccess;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public boolean loginSuccess() {
        return loginSuccess;
    }
}
