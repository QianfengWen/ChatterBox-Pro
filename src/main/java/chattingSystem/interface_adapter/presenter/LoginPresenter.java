package main.java.chattingSystem.interface_adapter.presenter;

import main.java.chattingSystem.entities.ChatRoom.ChatRoom;
import main.java.chattingSystem.frameworks_drivers.ui.views.ChatRoomView;
import main.java.chattingSystem.frameworks_drivers.ui.views.ViewManager;
import main.java.chattingSystem.interface_adapter.state.ChatRoomState;
import main.java.chattingSystem.interface_adapter.state.LoginState;
import main.java.chattingSystem.interface_adapter.view_models.ChatRoomViewModel;
import main.java.chattingSystem.interface_adapter.view_models.LoginViewModel;
import main.java.chattingSystem.interface_adapter.view_models.ViewManagerModel;
import main.java.chattingSystem.use_cases.login.LoginOutputData;
import main.java.chattingSystem.use_cases.login.LoginOutputBoundary;

import java.util.Objects;

import static main.java.chattingSystem.App.ChatRoomFrameFactory.createChatRoomFrame;

public class LoginPresenter implements LoginOutputBoundary{
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ViewManager viewManager;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel,
                          ViewManager viewManager) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.viewManager = viewManager;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // TODO replace the logic to load chatroom and create a new chatroom view for current user
        if (viewManagerModel.getActiveView().equals("log in")) {
            loginViewModel.firePropertyChanged();
            String username = response.getUsername();
            ChatRoomViewModel chatRoomViewModel =  new ChatRoomViewModel();
            ChatRoomState chatRoomState = new ChatRoomState();
            chatRoomState.setUsername(username);
            chatRoomViewModel.setChatRoomIdLabel("0");
            chatRoomViewModel.setUserNameLabel(username);
            chatRoomViewModel.setState(chatRoomState);
            chatRoomViewModel.firePropertyChanged();
            // create a new chat room view
            createChatRoomFrame(chatRoomViewModel);
        }



    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
