package main.java.chattingSystem.interface_adapter.state;

import main.java.chattingSystem.entities.User.User;

public class ChatRoomState {

    private String messageHistory = "";
    private String chatRoomName = "";
    private String userName = "";

    public void setUsername(String username) {
        userName = username;
    }
    public String getUsername() {
        return userName;
    }

    public void ChatRoomName(User user) {
        chatRoomName = user.getUsername();
    }


    public void setMessageHistory(String messageHistory) {
        this.messageHistory = messageHistory;
    }
}
