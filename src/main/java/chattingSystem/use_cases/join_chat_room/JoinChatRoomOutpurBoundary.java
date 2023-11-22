package chattingSystem.use_cases.join_chat_room;

import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;

import java.io.IOException;

public interface JoinChatRoomOutpurBoundary {
    void prepareSuccessView(JoinChatRoomOutputData joinChatRoomOutputData, LogOutDataAccessBoundary logOutDataAccessBoundary) throws IOException;

    void prepareFailView(JoinChatRoomOutputData joinChatRoomOutputData);
}
