package main.java.chattingSystem.interface_adapter.controllers;

import main.java.chattingSystem.entities.User.User;
import main.java.chattingSystem.use_cases.create_chat_room.CreateChatRoomInputBoundary;
import main.java.chattingSystem.use_cases.create_chat_room.CreateChatRoomInputData;
import main.java.chattingSystem.use_cases.signup.SignupInputBoundary;
import main.java.chattingSystem.use_cases.signup.SignupInputData;

public class CreateChatRoomController {
    final CreateChatRoomInputBoundary CreateChatRoomInteractor;
    public CreateChatRoomController(CreateChatRoomInputBoundary CreateChatRoomInteractor) {
        this.CreateChatRoomInteractor = CreateChatRoomInteractor;
    }

    public void execute() {
        CreateChatRoomInputData CreateChatRoomInputData = new CreateChatRoomInputData();
        CreateChatRoomInteractor.createChatRoom(CreateChatRoomInputData);
    }
}
