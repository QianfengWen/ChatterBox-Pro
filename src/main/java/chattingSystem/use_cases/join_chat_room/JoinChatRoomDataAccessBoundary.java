package chattingSystem.use_cases.join_chat_room;

import chattingSystem.entities.User.User;

import java.io.IOException;
import java.util.List;

public interface JoinChatRoomDataAccessBoundary {
    boolean existsById(String Id);
    void joinChatRoom(User user) throws IOException;

    List<String> fetchAllMessages();
}
