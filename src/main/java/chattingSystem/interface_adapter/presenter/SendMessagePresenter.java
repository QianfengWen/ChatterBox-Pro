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
    }

    @Override
    public void prepareFailView(String error) {
    }
}
