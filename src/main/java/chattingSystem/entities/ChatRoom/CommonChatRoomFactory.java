package main.java.chattingSystem.entities.ChatRoom;

import main.java.chattingSystem.entities.Message.TextMessage;
import main.java.chattingSystem.entities.User.User;

import java.time.LocalDateTime;
import java.util.List;

public class CommonChatRoomFactory implements ChatRoomFactory{
    @Override
    public ChatRoom create(String id, LocalDateTime creationTime) {
        return new CommonChatRoom(id, creationTime);
    }

}
