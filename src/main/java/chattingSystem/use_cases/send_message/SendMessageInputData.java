package chattingSystem.use_cases.send_message;

import java.time.LocalDateTime;

public class SendMessageInputData {
    private String username;
    private String senderID;
    private String message;
    private LocalDateTime timestamp;

    public SendMessageInputData(String username, String senderID, String message, LocalDateTime timestamp) {
        this.username = username;
        this.senderID = senderID;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getUsername() {return username;}
    public String getSenderID() {return senderID;}
    public String getMessage() {return message;}
    public LocalDateTime getTimestap() {return timestamp;}
}
