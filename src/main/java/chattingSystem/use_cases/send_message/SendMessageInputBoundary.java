package chattingSystem.use_cases.send_message;

import java.io.IOException;

public interface SendMessageInputBoundary {
    void execute(SendMessageInputData sendMessageInputData, String chatRoomId) throws IOException;
}
