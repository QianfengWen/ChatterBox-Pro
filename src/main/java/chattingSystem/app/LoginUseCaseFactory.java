package chattingSystem.app;


import chattingSystem.entities.User.CommonUserFactory;
import chattingSystem.entities.User.UserFactory;
import chattingSystem.frameworks_drivers.data_access.FileUserDataAccessObject;
import chattingSystem.frameworks_drivers.ui.views.LoginView;
import chattingSystem.frameworks_drivers.ui.views.ViewManager;
import chattingSystem.interface_adapter.controllers.JoinChatRoomController;
import chattingSystem.interface_adapter.controllers.LoginController;
import chattingSystem.interface_adapter.presenter.ChatRoomPresenter;
import chattingSystem.interface_adapter.presenter.LoginPresenter;
import chattingSystem.interface_adapter.view_models.LoginViewModel;
import chattingSystem.interface_adapter.view_models.ViewManagerModel;
import chattingSystem.use_cases.get_chat_room.GetChatRoomDataAccessBoundary;
import chattingSystem.use_cases.get_chat_room.GetUser;
import chattingSystem.use_cases.join_chat_room.JoinChatRoomDataAccessBoundary;
import chattingSystem.use_cases.join_chat_room.JoinChatRoomInputBoundary;
import chattingSystem.use_cases.join_chat_room.JoinChatRoomInteractor;
import chattingSystem.use_cases.join_chat_room.JoinChatRoomOutputBoundary;
import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import chattingSystem.use_cases.login.LoginInputBoundary;
import chattingSystem.use_cases.login.LoginInteractor;
import chattingSystem.use_cases.login.LoginOutputBoundary;
import chattingSystem.use_cases.login.LoginUserDataAccessInterface;
import chattingSystem.use_cases.refresh_messages.RefreshMessagesDataAccessBoundary;
import chattingSystem.use_cases.send_message.SendMessageUserDataAccessInterface;


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
            SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface,
            RefreshMessagesDataAccessBoundary refreshMessagesDataAccessBoundary
    ) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, viewManager, loginViewModel, logOutDataAccessBoundary,userDataAccessObject);
            JoinChatRoomController joinChatRoomController = createJoinChatRoomUseCase(joinChatRoomDataAccessBoundary, getChatRoomDataAccessBoundary, logOutDataAccessBoundary, getUser, sendMessageUserDataAccessInterface, refreshMessagesDataAccessBoundary);
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
            GetUser getUser, SendMessageUserDataAccessInterface sendMessageUserDataAccessInterface,
            RefreshMessagesDataAccessBoundary refreshMessagesDataAccessBoundary) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        JoinChatRoomOutputBoundary joinChatRoomOutputBoundary = new ChatRoomPresenter();

        UserFactory userFactory = new CommonUserFactory();

        JoinChatRoomInputBoundary joinChatRoomInteractor = new JoinChatRoomInteractor(joinChatRoomOutputBoundary,
                joinChatRoomDataAccessBoundary,
                getChatRoomDataAccessBoundary,
                logOutDataAccessBoundary,
                getUser,
                sendMessageUserDataAccessInterface,
                refreshMessagesDataAccessBoundary
                );


        return new JoinChatRoomController(joinChatRoomInteractor);
    }
}
