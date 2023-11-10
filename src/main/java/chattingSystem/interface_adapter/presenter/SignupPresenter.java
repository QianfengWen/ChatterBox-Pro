package main.java.chattingSystem.interface_adapter.presenter;



import main.java.chattingSystem.interface_adapter.state.SignupState;
import main.java.chattingSystem.interface_adapter.view_models.SignupViewModel;
import main.java.chattingSystem.interface_adapter.view_models.ViewManagerModel;
import main.java.chattingSystem.use_cases.signup.SignupOutputBoundary;
import main.java.chattingSystem.use_cases.signup.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the login view.
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

//        LoginState loginState = loginViewModel.getState();
//        loginState.setUsername(response.getUsername());
//        this.loginViewModel.setState(loginState);
//        loginViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(loginViewModel.getViewName());
        signupViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareNameFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void prepareInvalidPWFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setPasswordError(error);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void prepareRepeatPWFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setRepeatPasswordError(error);
        signupViewModel.firePropertyChanged();
    }
}