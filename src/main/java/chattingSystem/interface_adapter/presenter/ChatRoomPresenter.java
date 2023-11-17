package main.java.chattingSystem.interface_adapter.presenter;

import main.java.chattingSystem.entities.ChatRoom.ChatRoom;
import main.java.chattingSystem.entities.User.User;
import main.java.chattingSystem.frameworks_drivers.ui.views.ChatRoomView;
import main.java.chattingSystem.frameworks_drivers.ui.views.ChatRoomViewManager;
import main.java.chattingSystem.frameworks_drivers.ui.views.ViewManager;
import main.java.chattingSystem.interface_adapter.controllers.LogOutController;
import main.java.chattingSystem.interface_adapter.state.ChatRoomState;
import main.java.chattingSystem.interface_adapter.state.LoginState;
import main.java.chattingSystem.interface_adapter.view_models.ChatRoomViewManagerModel;
import main.java.chattingSystem.interface_adapter.view_models.ChatRoomViewModel;
import main.java.chattingSystem.interface_adapter.view_models.LoginViewModel;
import main.java.chattingSystem.interface_adapter.view_models.ViewManagerModel;
import main.java.chattingSystem.use_cases.join_chat_room.JoinChatRoomOutpurBoundary;
import main.java.chattingSystem.use_cases.join_chat_room.JoinChatRoomOutputData;
import main.java.chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import main.java.chattingSystem.use_cases.log_out.LogOutOutputBoundary;
import main.java.chattingSystem.use_cases.log_out.LogOutOutputData;
import main.java.chattingSystem.use_cases.send_message.SendMessageOutputBoundary;
import main.java.chattingSystem.use_cases.signup.SignupOutputData;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static main.java.chattingSystem.App.ChatRoomFrameFactory.createChatRoomFrame;
import static main.java.chattingSystem.App.ChatRoomFrameFactory.deleteChatRoomFrame;

public class ChatRoomPresenter implements JoinChatRoomOutpurBoundary, LogOutOutputBoundary {
    // the presenter for the chat room, will be used for sending messages
    // and receiving messages
    private final ChatRoomViewModel chatRoomViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public ChatRoomPresenter() {
        // every time we create a new chat room presenter, we create a new chat room view model, so that different User could use the chat room
        this.chatRoomViewModel = new ChatRoomViewModel();
        this.viewManagerModel = new ViewManagerModel();
        this.loginViewModel = new LoginViewModel();
    }



    @Override
    public void prepareSuccessView(JoinChatRoomOutputData joinChatRoomOutputData, LogOutDataAccessBoundary logOutDataAccessBoundary){
        ChatRoomState chatRoomState = new ChatRoomState();
        User user = joinChatRoomOutputData.getUser();
        String username = user.getUsername();
        chatRoomState.setUsername(username);
        chatRoomViewModel.setChatRoomIdLabel(joinChatRoomOutputData.getChatRoomId());
        chatRoomViewModel.setUserNameLabel(username);
        this.chatRoomViewModel.setState(chatRoomState);
        this.chatRoomViewModel.firePropertyChanged();
        createChatRoomFrame(chatRoomViewModel, logOutDataAccessBoundary);
    }

    @Override
    public void prepareFailView(JoinChatRoomOutputData joinChatRoomOutputData) {
    }
    @Override
    public void prepareSuccessView(LogOutOutputData logOutOutputData){
        ChatRoomState chatRoomState = new ChatRoomState();
        this.chatRoomViewModel.setState(chatRoomState);
        this.chatRoomViewModel.firePropertyChanged();
        deleteChatRoomFrame();
        this.viewManagerModel.setActiveView(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareFailView(LogOutOutputData logOutOutputData) {
    }
}
