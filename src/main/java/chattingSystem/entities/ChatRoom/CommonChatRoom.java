package main.java.chattingSystem.entities.ChatRoom;

import main.java.chattingSystem.entities.Message.TextMessage;
import main.java.chattingSystem.entities.User.User;

import java.time.LocalDateTime;
import java.util.List;

public class CommonChatRoom implements ChatRoom {
    private final List<User> member;

    private final List<TextMessage> chatHistory;

    private final String chatRoomID;

    private LocalDateTime creationTime;

    CommonChatRoom(String chatRoomID, LocalDateTime creationTime) {
        this.chatRoomID = chatRoomID;
        this.creationTime = creationTime;
        this.member = new java.util.ArrayList<>();
        this.chatHistory = new java.util.ArrayList<>();
    }

    @Override
    public List<User> getMembers() {
        return member;
    }

    @Override
    public String getMembersString() {
        StringBuilder memberString = new StringBuilder();
        for (User user : member) {
            memberString.append(user.getUsername()).append(" ");
        }
        return memberString.toString();
    }

    public List<TextMessage> getChatHistory() {
        return chatHistory;
    }

    @Override
    public String getChatHistoryString() {
        StringBuilder chatHistoryString = new StringBuilder();
        for (TextMessage message : chatHistory) {
            chatHistoryString.append(message.toString()).append("\n");
        }
        return chatHistoryString.toString();
    }


    @Override
    public void addMessage(TextMessage message) {
        chatHistory.add(message);
    }
    @Override
    public void addMember(User user) {
        member.add(user);
    }

    @Override
    public String getChatRoomID() {
        return chatRoomID;
    }


}
