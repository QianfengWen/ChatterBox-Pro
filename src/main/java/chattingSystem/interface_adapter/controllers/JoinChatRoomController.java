package chattingSystem.interface_adapter.controllers;

import chattingSystem.entities.User.User;
import chattingSystem.use_cases.join_chat_room.JoinChatRoomInputBoundary;
import chattingSystem.use_cases.join_chat_room.JoinChatRoomInputData;

import java.io.IOException;

public class JoinChatRoomController {
    final JoinChatRoomInputBoundary JoinChatRoomInteractor;
    public JoinChatRoomController(JoinChatRoomInputBoundary JoinChatRoomInteractor) {
        this.JoinChatRoomInteractor = JoinChatRoomInteractor;
    }
    public void execute(String username) throws IOException {
        JoinChatRoomInputData joinChatRoomInputData = new JoinChatRoomInputData(username);
        JoinChatRoomInteractor.execute(joinChatRoomInputData);
    }
}
