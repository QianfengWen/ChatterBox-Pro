package main.java.chattingSystem.App;

import main.java.chattingSystem.entities.ChatRoom.ChatRoomFactory;
import main.java.chattingSystem.entities.ChatRoom.CommonChatRoomFactory;
import main.java.chattingSystem.entities.User.CommonUserFactory;
import main.java.chattingSystem.entities.User.UserFactory;
import main.java.chattingSystem.frameworks_drivers.data_access.FileUserDataAccessObject;
import main.java.chattingSystem.frameworks_drivers.ui.views.ChatRoomView;
import main.java.chattingSystem.frameworks_drivers.ui.views.LoginView;
import main.java.chattingSystem.frameworks_drivers.ui.views.SignupView;
import main.java.chattingSystem.frameworks_drivers.ui.views.ViewManager;
import main.java.chattingSystem.interface_adapter.controllers.CreateChatRoomController;
import main.java.chattingSystem.interface_adapter.controllers.SignupController;
import main.java.chattingSystem.interface_adapter.presenter.ChatRoomPresenter;
import main.java.chattingSystem.interface_adapter.view_models.ChatRoomViewModel;
import main.java.chattingSystem.interface_adapter.view_models.LoginViewModel;
import main.java.chattingSystem.interface_adapter.view_models.SignupViewModel;
import main.java.chattingSystem.interface_adapter.view_models.ViewManagerModel;
import main.java.chattingSystem.interface_adapter.presenter.SignupPresenter;
import main.java.chattingSystem.use_cases.create_chat_room.CreateChatRoomDataAccessBoundary;
import main.java.chattingSystem.use_cases.create_chat_room.CreateChatRoomInputBoundary;
import main.java.chattingSystem.use_cases.create_chat_room.CreateChatRoomInteractor;
import main.java.chattingSystem.use_cases.create_chat_room.CreateChatRoomOutputBoundary;
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
            FileUserDataAccessObject userDataAccessObject,
            CreateChatRoomDataAccessBoundary createChatRoomDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            CreateChatRoomController createChatRoomController = createChatRoomController(createChatRoomDataAccessObject);
            return new SignupView(signupController, createChatRoomController, signupViewModel, viewManagerModel);
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
    private static CreateChatRoomController createChatRoomController(
            CreateChatRoomDataAccessBoundary createChatRoomDataAccessBoundary
    ) throws IOException {

        CreateChatRoomOutputBoundary chatRoomOutputBoundary = new ChatRoomPresenter();

        ChatRoomFactory chatRoomFactory = new CommonChatRoomFactory();

        CreateChatRoomInputBoundary createChatRoomInteractor = new CreateChatRoomInteractor(
                chatRoomOutputBoundary, chatRoomFactory, createChatRoomDataAccessBoundary);

        return new CreateChatRoomController(createChatRoomInteractor);
    }
}
