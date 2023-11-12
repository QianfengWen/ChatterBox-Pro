package main.java.chattingSystem.use_cases.get_chat_room;

import main.java.chattingSystem.entities.ChatRoom.ChatRoom;

public interface GetChatRoomDataAccessBoundary {
    ChatRoom getChatRoom(String id);
}
