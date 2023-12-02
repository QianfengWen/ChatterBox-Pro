package chattingSystem.App;

import chattingSystem.entities.User.CommonUserFactory;
import chattingSystem.entities.User.UserFactory;
import chattingSystem.frameworks_drivers.data_access.FileUserDataAccessObject;
import chattingSystem.frameworks_drivers.ui.views.SignupView;
import chattingSystem.interface_adapter.controllers.SignupController;
import chattingSystem.interface_adapter.view_models.LoginViewModel;
import chattingSystem.interface_adapter.view_models.SignupViewModel;
import chattingSystem.interface_adapter.view_models.ViewManagerModel;
import chattingSystem.interface_adapter.presenter.SignupPresenter;
import chattingSystem.use_cases.signup.SignupInputBoundary;
import chattingSystem.use_cases.signup.SignupInteractor;
import chattingSystem.use_cases.signup.SignupOutputBoundary;
import chattingSystem.use_cases.signup.SignupUserDataAccessInterface;

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
