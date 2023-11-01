package main.java.chattingSystem.entities.ChatRoom;

public interface ChatRoomFactory {
    ChatRoom create(List<User> member, List<Messsage> chatHistory);
}
