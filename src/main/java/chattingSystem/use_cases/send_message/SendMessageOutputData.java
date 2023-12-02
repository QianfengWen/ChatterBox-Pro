package chattingSystem.use_cases.send_message;


import chattingSystem.entities.ChatRoom.CommonChatRoom;
import chattingSystem.entities.User.User;


public class SendMessageOutputData {
    private final boolean useCaseFailed;

    private final String chatRoomId;

    private final String senderId;

    private final String username;

    private final String message;

    public SendMessageOutputData(String senderId, String username, String chatRoomId, String message, boolean useCaseFailed) {
        this.username = username;
        this.senderId = senderId;
        this.chatRoomId = chatRoomId;
        this.message = message;
        this.useCaseFailed = useCaseFailed;
    }

    public String getChatRoomId() {return chatRoomId;}
    public String getUsername() {return username;}
    public String getMessage() {return message;}
    public String getSenderId() {return senderId;}

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
