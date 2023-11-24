package chattingSystem.entities.ChatRoom;


import chattingSystem.entities.Message.TextMessage;
import chattingSystem.entities.User.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatRoomFactory {

    ChatRoom create(String id, List<User> Members, LocalDateTime creationTime);
}
