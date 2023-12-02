package chattingSystem.use_cases.signup;

import chattingSystem.entities.Password.Password;

public class SignupInputData {
    final private String username;
    final private Password password;
    final private Password repeatPassword;

    public SignupInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = new Password(password);
        this.repeatPassword = new Password(repeatPassword);
    }

    String getUsername() {
        return username;
    }

    public String getPassword() {
        return password.getPassword();
    }

    public Boolean isValidPassword() {
        return password.isValid();
    }

    public String getRepeatPassword() {
        return repeatPassword.getPassword();
    }
}
