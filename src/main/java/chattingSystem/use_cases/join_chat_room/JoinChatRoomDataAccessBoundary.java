package chattingSystem.use_cases.join_chat_room;

import chattingSystem.entities.User.User;

import java.io.IOException;

public interface JoinChatRoomDataAccessBoundary {
    boolean existsById(String Id);
    void joinChatRoom(User user) throws IOException;
}
