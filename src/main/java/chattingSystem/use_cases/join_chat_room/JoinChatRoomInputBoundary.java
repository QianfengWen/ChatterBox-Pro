package chattingSystem.use_cases.join_chat_room;

import java.io.IOException;

public interface JoinChatRoomInputBoundary {
    void execute(JoinChatRoomInputData inputData) throws IOException;
}
