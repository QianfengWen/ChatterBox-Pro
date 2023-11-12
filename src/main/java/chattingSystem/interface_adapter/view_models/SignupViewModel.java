package main.java.chattingSystem.interface_adapter.view_models;

import main.java.chattingSystem.interface_adapter.state.SignupState;
import main.java.chattingSystem.interface_adapter.state.ViewState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Sign Up";
    public static final String USERNAME_LABEL = "Choose username";

    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public static final String LOGIN_BUTTON_LABEL = "Log in";

    private SignupState state = new SignupState();

    public SignupViewModel() {
        super("sign up");
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SignupState getState() {
        return state;
    }

    public String getSignUpTitle() {
        return TITLE_LABEL;
    }

    public String getPasswordHint() {
        return "Password must be at least 8 characters long, contain at least one number, and contain at least one special character";
    }

    public String getCancelButtonText() {
        return CANCEL_BUTTON_LABEL;
    }

    public String getSignUpButtonText() {
        return SIGNUP_BUTTON_LABEL;
    }
}
