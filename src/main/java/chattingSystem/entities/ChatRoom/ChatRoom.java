package main.java.chattingSystem.entities.ChatRoom;

import main.java.chattingSystem.entities.Message.TextMessage;
import main.java.chattingSystem.entities.User.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatRoom {

    List<User> getMembers();
    String getMembersString();
    String getMembersIdString();

    String getChatHistory();
    String getChatHistoryString();
    void addMessage(TextMessage message);
    void addMember(User user);
    String getChatRoomID();

    LocalDateTime getCreationTime();
}
