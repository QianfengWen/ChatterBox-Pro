package main.java.chattingSystem.App;

import main.java.chattingSystem.entities.User.CommonUserFactory;
import main.java.chattingSystem.entities.User.UserFactory;
import main.java.chattingSystem.frameworks_drivers.data_access.FileUserDataAccessObject;
import main.java.chattingSystem.frameworks_drivers.ui.views.SignupView;
import main.java.chattingSystem.interface_adapter.controllers.SignupController;
import main.java.chattingSystem.interface_adapter.view_models.LoginViewModel;
import main.java.chattingSystem.interface_adapter.view_models.SignupViewModel;
import main.java.chattingSystem.interface_adapter.view_models.ViewManagerModel;
import main.java.chattingSystem.interface_adapter.presenter.SignupPresenter;
import main.java.chattingSystem.use_cases.signup.SignupInputBoundary;
import main.java.chattingSystem.use_cases.signup.SignupInteractor;
import main.java.chattingSystem.use_cases.signup.SignupOutputBoundary;
import main.java.chattingSystem.use_cases.signup.SignupUserDataAccessInterface;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {
    private SignupUseCaseFactory() {}

    public static SignupView create(
            ViewManagerModel viewManagerModel,
            SignupViewModel signupViewModel,
            LoginViewModel loginViewModel,
            FileUserDataAccessObject userDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            return new SignupView(signupController, signupViewModel, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel,SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}
