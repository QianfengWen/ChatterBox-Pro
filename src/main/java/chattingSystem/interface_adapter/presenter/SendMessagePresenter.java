package main.java.chattingSystem.interface_adapter.presenter;

import main.java.chattingSystem.interface_adapter.state.ChatRoomState;
import main.java.chattingSystem.interface_adapter.view_models.ChatRoomViewModel;
import main.java.chattingSystem.use_cases.send_message.SendMessageOutputBoundary;
import main.java.chattingSystem.use_cases.send_message.SendMessageOutputData;

public class SendMessagePresenter implements SendMessageOutputBoundary {

    private final ChatRoomViewModel chatRoomViewModel;

    public SendMessagePresenter(ChatRoomViewModel chatRoomViewModel) {
        this.chatRoomViewModel = chatRoomViewModel;
    }
    @Override
    public void prepareSuccessView(SendMessageOutputData chatroomOutputData) {
        ChatRoomState currentState = chatRoomViewModel.getState();
        currentState.setSenderId(chatroomOutputData.getSenderId());
        currentState.setUsername(chatroomOutputData.getUsername());
        currentState.setChatRoomId(chatroomOutputData.getChatRoomId());
        currentState.addMessage(chatroomOutputData.getUsername() + " : " + chatroomOutputData.getMessage());
        chatRoomViewModel.setState(currentState);
        chatRoomViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
