package main.java.chattingSystem.entities.ChatRoom;

import java.util.List;

public class CommonChatRoom implements ChatRoom {
    private final List<User> member;

    private final List<Message> chatHistory;

    CommonChatRoom(List<User> member, List<Message> chatHistory) {
        this.chatHistory = chatHistory;
        this.member = member;
    }

    public List<User> getMember() {
        return member;
    }

    public list<Message> getChatHistory() {
        return chatHistory;
    }
}
