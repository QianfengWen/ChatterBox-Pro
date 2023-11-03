package main.java.chattingSystem.entities.ChatRoom;

import main.java.chattingSystem.entities.Message.TextMessage;
import main.java.chattingSystem.entities.User.User;

import java.util.List;

public class CommonChatRoomFactory implements ChatRoomFactory{
    @Override
    public ChatRoom create(List<User> member, List<TextMessage> chatHistory) {
        return new CommonChatRoom(member, chatHistory);
    }
}
