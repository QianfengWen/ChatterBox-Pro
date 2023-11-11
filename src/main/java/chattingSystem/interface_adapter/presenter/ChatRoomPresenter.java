package main.java.chattingSystem.interface_adapter.presenter;

import main.java.chattingSystem.entities.ChatRoom.ChatRoom;
import main.java.chattingSystem.entities.User.User;
import main.java.chattingSystem.frameworks_drivers.ui.views.ChatRoomView;
import main.java.chattingSystem.frameworks_drivers.ui.views.ChatRoomViewManager;
import main.java.chattingSystem.frameworks_drivers.ui.views.ViewManager;
import main.java.chattingSystem.interface_adapter.state.ChatRoomState;
import main.java.chattingSystem.interface_adapter.state.LoginState;
import main.java.chattingSystem.interface_adapter.view_models.ChatRoomViewManagerModel;
import main.java.chattingSystem.interface_adapter.view_models.ChatRoomViewModel;
import main.java.chattingSystem.interface_adapter.view_models.ViewManagerModel;
import main.java.chattingSystem.use_cases.create_chat_room.CreateChatRoomOutputBoundary;
import main.java.chattingSystem.use_cases.create_chat_room.CreateChatRoomOutputData;
import main.java.chattingSystem.use_cases.join_chat_room.JoinChatRoomOutpurBoundary;
import main.java.chattingSystem.use_cases.join_chat_room.JoinChatRoomOutputData;
import main.java.chattingSystem.use_cases.send_message.SendMessageOutputBoundary;
import main.java.chattingSystem.use_cases.signup.SignupOutputData;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static main.java.chattingSystem.App.ChatRoomFrameFactory.createChatRoomFrame;

public class ChatRoomPresenter implements CreateChatRoomOutputBoundary, JoinChatRoomOutpurBoundary {
    // the presenter for the chat room, will be used for sending messages
    // and receiving messages
    private final ChatRoomViewModel chatRoomViewModel;

    public ChatRoomPresenter() {
        // every time we create a new chat room presenter, we create a new chat room view model, so that different User could use the chat room
        this.chatRoomViewModel = new ChatRoomViewModel();
    }

    @Override
    public void prepareFailView(String s) {
    }



    @Override
    public void prepareSuccessView(CreateChatRoomOutputData response) {
    }

    @Override
    public void prepareSuccessView(JoinChatRoomOutputData joinChatRoomOutputData){
        ChatRoomState chatRoomState = new ChatRoomState();
        User user = joinChatRoomOutputData.getUser();
        String username = user.getUsername();
        chatRoomState.setUsername(username);
        chatRoomViewModel.setChatRoomIdLabel(joinChatRoomOutputData.getChatRoomId());
        chatRoomViewModel.setUserNameLabel(username);
        this.chatRoomViewModel.setState(chatRoomState);
        this.chatRoomViewModel.firePropertyChanged();
        createChatRoomFrame(chatRoomViewModel);
    }

    @Override
    public void prepareFailView(JoinChatRoomOutputData joinChatRoomOutputData) {
    }
}
