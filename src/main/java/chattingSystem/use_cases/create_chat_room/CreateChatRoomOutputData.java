package main.java.chattingSystem.use_cases.create_chat_room;

import main.java.chattingSystem.entities.ChatRoom.ChatRoom;

public class CreateChatRoomOutputData {
    private String chatRoomId;
    public CreateChatRoomOutputData(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }
    public String getChatRoomId() {
        return chatRoomId;
    }
}
