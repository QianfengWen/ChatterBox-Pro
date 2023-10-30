package main.java.chattingSystem.entities.ChatRoom;

public interface ChatRoom {

    list<User> getMember();

    list<Message> getChatHistory();
}
