package chattingSystem.interface_adapter.presenter;

import chattingSystem.interface_adapter.state.ChatRoomState;
import chattingSystem.interface_adapter.view_models.ChatRoomViewModel;
import chattingSystem.use_cases.refresh_messages.RefreshMessageOutputData;
import chattingSystem.use_cases.refresh_messages.RefreshMessagesOutputBoundary;

public class RefreshMessagePresenter implements RefreshMessagesOutputBoundary {
    private ChatRoomViewModel chatRoomViewModel;
    private ChatRoomState chatRoomState;
    public RefreshMessagePresenter(ChatRoomViewModel chatRoomViewModel, ChatRoomState chatRoomState) {
        this.chatRoomViewModel = chatRoomViewModel;
        this.chatRoomState = chatRoomState;
    }

    @Override
    public void refreshSuccessView(RefreshMessageOutputData refreshMessageOutputData) {
        chatRoomState.setMessage(refreshMessageOutputData.getMessags());
        chatRoomViewModel.setState(chatRoomState);
    }
}
