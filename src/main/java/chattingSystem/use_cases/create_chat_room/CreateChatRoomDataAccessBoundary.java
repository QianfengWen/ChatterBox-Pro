package main.java.chattingSystem.use_cases.create_chat_room;

import main.java.chattingSystem.entities.ChatRoom.ChatRoom;

public interface CreateChatRoomDataAccessBoundary {
    boolean existsById(String id);
    void save(ChatRoom chatRoom);
}
