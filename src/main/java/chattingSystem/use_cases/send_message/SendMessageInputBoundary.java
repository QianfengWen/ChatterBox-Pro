package chattingSystem.use_cases.send_message;

public interface SendMessageInputBoundary {
    void execute(SendMessageInputData sendMessageInputData, String chatRoomId);
}
