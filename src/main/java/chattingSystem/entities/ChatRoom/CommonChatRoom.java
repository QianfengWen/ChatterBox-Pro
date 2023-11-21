package main.java.chattingSystem.entities.ChatRoom;

import main.java.chattingSystem.entities.Message.Message;
import main.java.chattingSystem.entities.Message.TextMessage;
import main.java.chattingSystem.entities.User.CommonUser;
import main.java.chattingSystem.entities.User.User;

import java.time.LocalDateTime;
import java.util.List;

public class CommonChatRoom implements ChatRoom {
    private final List<User> members;

    private String chatHistory;

    private final String chatRoomId;

    private LocalDateTime creationTime;


    CommonChatRoom(String chatRoomId, List<User> Members, String chatHistory, LocalDateTime creationTime) {
        this.chatRoomId = chatRoomId;
        this.members = Members;
        this.chatHistory = chatHistory;
        this.creationTime = creationTime;
    }

    @Override
    public List<User> getMembers() {
        return members;
    }

    @Override
    public String getMembersString() {
        StringBuilder memberString = new StringBuilder();
        for (User user : members) {
            memberString.append(user.getUsername()).append(" ");
        }
        return memberString.toString();
    }

    @Override
    public String getMembersIdString() {
        StringBuilder memberString = new StringBuilder();
        for (User user : members) {
            // The same bug as in ChatRoomDataAccessObject.java
            if (user == null) {
                // This handles when the admin was set to null when the program runs not for the first time.
                continue;
            }
            memberString.append(user.getUserid()).append(" ");
        }
        return memberString.toString();
    }

    public String getChatHistory() {
        return chatHistory;
    }

    @Override
    public String getChatHistoryString() {
//        StringBuilder chatHistoryString = new StringBuilder();
//        for (TextMessage message : chatHistory) {
//            chatHistoryString.append(message.toString()).append("\n");
//        }
//        return chatHistoryString.toString();
        return "Test";
    }


    @Override
    public void addMessage(Message message) {

//        chatHistory.add(message);
        return;
    }
    @Override
    public void addMember(User user) {
        members.add(user);
    }

    @Override
    public String getChatRoomID() {
        return chatRoomId;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }


}
