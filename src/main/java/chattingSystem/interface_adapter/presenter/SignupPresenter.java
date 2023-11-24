package chattingSystem.interface_adapter.presenter;



import chattingSystem.interface_adapter.state.LoginState;
import chattingSystem.interface_adapter.state.SignupState;
import chattingSystem.interface_adapter.view_models.LoginViewModel;
import chattingSystem.interface_adapter.view_models.SignupViewModel;
import chattingSystem.interface_adapter.view_models.ViewManagerModel;
import chattingSystem.use_cases.signup.SignupOutputBoundary;
import chattingSystem.use_cases.signup.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    private final LoginViewModel loginViewModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the login view.
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        signupViewModel.firePropertyChanged();

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        loginState.setSignupSuccess(true);
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
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