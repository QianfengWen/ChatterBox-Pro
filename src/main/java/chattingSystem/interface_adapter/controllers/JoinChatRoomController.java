package main.java.chattingSystem.interface_adapter.controllers;

import main.java.chattingSystem.entities.User.User;
import main.java.chattingSystem.use_cases.join_chat_room.JoinChatRoomInputBoundary;
import main.java.chattingSystem.use_cases.join_chat_room.JoinChatRoomInputData;

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
