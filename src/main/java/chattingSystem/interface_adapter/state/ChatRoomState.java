package chattingSystem.interface_adapter.state;


import chattingSystem.entities.User.User;
import java.util.ArrayList;
import java.util.List;


public class ChatRoomState {

    private List<String> messageHistory = new ArrayList<>();

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

    public void setMessage(List<String> messages) {this.messageHistory = messages;}

    public String getChatRoomId() {return chatRoomId;}

    public List<String> getMessageHistory() {
        return messageHistory;
    }

    public String getSenderId() {return senderId;}


}
