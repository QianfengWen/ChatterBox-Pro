package main.java.chattingSystem.entities.ChatRoom;


import main.java.chattingSystem.entities.Message.TextMessage;
import main.java.chattingSystem.entities.User.User;

import java.util.List;

public interface ChatRoomFactory {

    ChatRoom create(List<User> member, List<TextMessage> chatHistory);
}
