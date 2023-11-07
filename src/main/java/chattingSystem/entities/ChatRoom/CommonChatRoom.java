package main.java.chattingSystem.entities.ChatRoom;

import main.java.chattingSystem.entities.Message.TextMessage;
import main.java.chattingSystem.entities.User.User;

import java.util.List;

public class CommonChatRoom implements ChatRoom {
    private final List<User> member;

    private final List<TextMessage> chatHistory;

    CommonChatRoom(List<User> member, List<TextMessage> chatHistory) {
        this.chatHistory = chatHistory;
        this.member = member;
    }

    public List<User> getMember() {
        return member;
    }

    public List<TextMessage> getChatHistory() {
        return chatHistory;
    }
}
