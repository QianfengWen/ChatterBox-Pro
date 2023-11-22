package chattingSystem.interface_adapter.presenter;

import chattingSystem.entities.ChatRoom.ChatRoom;
import chattingSystem.entities.User.User;
import chattingSystem.frameworks_drivers.ui.views.ChatRoomView;
import chattingSystem.frameworks_drivers.ui.views.ChatRoomViewManager;
import chattingSystem.frameworks_drivers.ui.views.ViewManager;
import chattingSystem.interface_adapter.controllers.LogOutController;
import chattingSystem.interface_adapter.state.ChatRoomState;
import chattingSystem.interface_adapter.state.LoginState;
import chattingSystem.interface_adapter.view_models.ChatRoomViewManagerModel;
import chattingSystem.interface_adapter.view_models.ChatRoomViewModel;
import chattingSystem.interface_adapter.view_models.LoginViewModel;
import chattingSystem.interface_adapter.view_models.ViewManagerModel;
import chattingSystem.use_cases.join_chat_room.JoinChatRoomOutpurBoundary;
import chattingSystem.use_cases.join_chat_room.JoinChatRoomOutputData;
import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import chattingSystem.use_cases.log_out.LogOutOutputBoundary;
import chattingSystem.use_cases.log_out.LogOutOutputData;
import chattingSystem.use_cases.send_message.SendMessageOutputBoundary;
import chattingSystem.use_cases.signup.SignupOutputData;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static chattingSystem.App.ChatRoomFrameFactory.createChatRoomFrame;

public class ChatRoomPresenter implements JoinChatRoomOutpurBoundary, LogOutOutputBoundary {
    // the presenter for the chat room, will be used for sending messages
    // and receiving messages
    private ViewManagerModel viewManagerModel;

    public ChatRoomPresenter() {
        // every time we create a new chat room presenter, we create a new chat room view model, so that different User could use the chat room
        this.viewManagerModel = new ViewManagerModel();
    }



    @Override
    public void prepareSuccessView(JoinChatRoomOutputData joinChatRoomOutputData, LogOutDataAccessBoundary logOutDataAccessBoundary){
        ChatRoomState chatRoomState = new ChatRoomState();
        ChatRoomViewModel chatRoomViewModel = new ChatRoomViewModel();
        User user = joinChatRoomOutputData.getUser();
        String username = user.getUsername();
        chatRoomState.setUsername(username);
        chatRoomViewModel.setChatRoomIdLabel(joinChatRoomOutputData.getChatRoomId());
        chatRoomViewModel.setUserNameLabel(username);
        chatRoomViewModel.setState(chatRoomState);
        chatRoomViewModel.firePropertyChanged();
        createChatRoomFrame(chatRoomViewModel, logOutDataAccessBoundary);
    }

    @Override
    public void prepareFailView(JoinChatRoomOutputData joinChatRoomOutputData) {
    }
    @Override
    public void prepareSuccessView(LogOutOutputData logOutOutputData){
        System.out.println("Log out successfully");

    }
    @Override
    public void prepareFailView(LogOutOutputData logOutOutputData) {
    }
}
