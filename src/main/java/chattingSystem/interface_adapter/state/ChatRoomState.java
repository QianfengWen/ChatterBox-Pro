package main.java.chattingSystem.interface_adapter.state;

import java.util.ArrayList;

public class ChatRoomState {

    private ArrayList<String> messageHistory = new ArrayList<>();

    private String userName = "";

    private String senderId = "";

    private String chatRoomId = "";

    public void setUsername(String username) {
        userName = username;
    }
    public String getUsername() {
        return userName;
    }

    public void setChatRoomId(String chatRoomId) {this.chatRoomId = chatRoomId;}
    public void setSenderId(String senderId) {this.senderId = senderId;}

    public void addMessage(String message) {this.messageHistory.add(message);}

    public String getChatRoomId() {return chatRoomId;}

    public ArrayList<String> getMessageHistory() {
        return messageHistory;
    }

    public String getSenderId() {return senderId;}


}
