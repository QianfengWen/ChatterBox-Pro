package main.java.chattingSystem.use_cases.join_chat_room;

import main.java.chattingSystem.entities.User.User;

public interface JoinChatRoomDataAccessBoundary {
    boolean existsById(String Id);
    void joinChatRoom(User user);
}
