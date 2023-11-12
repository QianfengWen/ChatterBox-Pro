package main.java.chattingSystem.use_cases.create_chat_room;

import main.java.chattingSystem.entities.ChatRoom.ChatRoom;
import main.java.chattingSystem.interface_adapter.view_models.ChatRoomViewModel;

public interface CreateChatRoomOutputBoundary {
    void prepareSuccessView(CreateChatRoomOutputData chatRoom);
    void prepareFailView(String error);
}
