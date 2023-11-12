package main.java.chattingSystem.use_cases.create_chat_room;

import main.java.chattingSystem.entities.Message.TextMessage;
import main.java.chattingSystem.entities.User.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateChatRoomInputData {
    private String chatRoomID;
    public CreateChatRoomInputData(){
        this.chatRoomID = "0";
    }

    public String getChatRoomID() {
        return chatRoomID;
    }
}
