package main.java.chattingSystem.entities.ChatRoom;

import com.sun.jdi.CharType;

public class CommonChatRoomFactory implements ChatRoomFactory{
    public ChatRoom create(List<User> member, List<Message> chatHistory) {
        return CommonChatRoom(member, chatHistory);
    }
}
