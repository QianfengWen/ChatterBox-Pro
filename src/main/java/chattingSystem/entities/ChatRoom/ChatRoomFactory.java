package main.java.chattingSystem.entities.ChatRoom;


import main.java.chattingSystem.entities.Message.TextMessage;
import main.java.chattingSystem.entities.User.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatRoomFactory {

    ChatRoom create(String id, LocalDateTime creationTime);
}
