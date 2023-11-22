package chattingSystem.entities.ChatRoom;

import chattingSystem.entities.Message.Message;
import chattingSystem.entities.Message.TextMessage;
import chattingSystem.entities.User.User;

import java.time.LocalDateTime;
import java.util.List;

public class CommonChatRoomFactory implements ChatRoomFactory{
    @Override
    public ChatRoom create(String id, List<User> Member, String Messages, LocalDateTime creationTime) {
        return new CommonChatRoom(id, Member, Messages, creationTime);
    }

}
