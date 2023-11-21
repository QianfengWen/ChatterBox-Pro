package main.java.chattingSystem.App;

import main.java.chattingSystem.entities.User.CommonUserFactory;
import main.java.chattingSystem.entities.User.UserFactory;
import main.java.chattingSystem.frameworks_drivers.data_access.FileUserDataAccessObject;
import main.java.chattingSystem.frameworks_drivers.ui.views.LoginView;
import main.java.chattingSystem.frameworks_drivers.ui.views.ViewManager;
import main.java.chattingSystem.interface_adapter.controllers.JoinChatRoomController;
import main.java.chattingSystem.interface_adapter.controllers.LoginController;
import main.java.chattingSystem.interface_adapter.presenter.ChatRoomPresenter;
import main.java.chattingSystem.interface_adapter.presenter.LoginPresenter;
import main.java.chattingSystem.interface_adapter.view_models.LoginViewModel;
import main.java.chattingSystem.interface_adapter.view_models.ViewManagerModel;
import main.java.chattingSystem.use_cases.get_chat_room.GetChatRoomDataAccessBoundary;
import main.java.chattingSystem.use_cases.get_chat_room.GetUser;
import main.java.chattingSystem.use_cases.join_chat_room.JoinChatRoomDataAccessBoundary;
import main.java.chattingSystem.use_cases.join_chat_room.JoinChatRoomInputBoundary;
import main.java.chattingSystem.use_cases.join_chat_room.JoinChatRoomInteractor;
import main.java.chattingSystem.use_cases.join_chat_room.JoinChatRoomOutpurBoundary;
import main.java.chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import main.java.chattingSystem.use_cases.login.LoginInputBoundary;
import main.java.chattingSystem.use_cases.login.LoginInteractor;
import main.java.chattingSystem.use_cases.login.LoginOutputBoundary;
import main.java.chattingSystem.use_cases.login.LoginUserDataAccessInterface;
import main.java.chattingSystem.use_cases.send_message.SendMessageUserDataAccessInterface;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            ViewManager viewManager,
            LoginViewModel loginViewModel,
            FileUserDataAccessObject userDataAccessObject,
            JoinChatRoomDataAccessBoundary joinChatRoomDataAccessBoundary,
            GetChatRoomDataAccessBoundary getChatRoomDataAccessBoundary,
            LogOutDataAccessBoundary logOutDataAccessBoundary,
            GetUser getUser,
            SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface
    ) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, viewManager, loginViewModel, logOutDataAccessBoundary,userDataAccessObject);
            JoinChatRoomController joinChatRoomController = createJoinChatRoomUseCase(joinChatRoomDataAccessBoundary, getChatRoomDataAccessBoundary, logOutDataAccessBoundary, getUser, sendMessageUserDataAccessInterface);
            return new LoginView(loginController, joinChatRoomController, loginViewModel, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            ViewManager viewManager,
            LoginViewModel loginViewModel,
            LogOutDataAccessBoundary logOutDataAccessBoundary,
            LoginUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loginViewModel,
                viewManager);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary, logOutDataAccessBoundary);

        return new LoginController(loginInteractor);
    }
    public static JoinChatRoomController createJoinChatRoomUseCase(
            JoinChatRoomDataAccessBoundary joinChatRoomDataAccessBoundary,
            GetChatRoomDataAccessBoundary getChatRoomDataAccessBoundary,
            LogOutDataAccessBoundary logOutDataAccessBoundary,
            GetUser getUser, SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        JoinChatRoomOutpurBoundary joinChatRoomOutpurBoundary = new ChatRoomPresenter();

        UserFactory userFactory = new CommonUserFactory();

        JoinChatRoomInputBoundary joinChatRoomInteractor = new JoinChatRoomInteractor(joinChatRoomOutpurBoundary,
                joinChatRoomDataAccessBoundary,
                getChatRoomDataAccessBoundary,
                logOutDataAccessBoundary,
                getUser,
                sendMessageUserDataAccessInterface);


        return new JoinChatRoomController(joinChatRoomInteractor);
    }
}
