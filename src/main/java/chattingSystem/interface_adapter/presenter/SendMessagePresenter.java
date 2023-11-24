package chattingSystem.interface_adapter.presenter;

import chattingSystem.interface_adapter.state.ChatRoomState;
import chattingSystem.interface_adapter.view_models.ChatRoomViewModel;
import chattingSystem.use_cases.send_message.SendMessageOutputBoundary;
import chattingSystem.use_cases.send_message.SendMessageOutputData;

public class SendMessagePresenter implements SendMessageOutputBoundary {

    private final ChatRoomViewModel chatRoomViewModel;

    public SendMessagePresenter(ChatRoomViewModel chatRoomViewModel) {
        this.chatRoomViewModel = chatRoomViewModel;
    }
    @Override
    public void prepareSuccessView(SendMessageOutputData chatroomOutputData) {
//        ChatRoomState currentState = chatRoomViewModel.getState();
//        currentState.setSenderId(chatroomOutputData.getSenderId());
//        currentState.setUsername(chatroomOutputData.getUsername());
//        currentState.setChatRoomId(chatroomOutputData.getChatRoomId());
//        chatRoomViewModel.setState(currentState);
//        chatRoomViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
