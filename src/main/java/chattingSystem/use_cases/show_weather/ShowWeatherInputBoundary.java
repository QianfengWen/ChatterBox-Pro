package chattingSystem.use_cases.show_weather;

import chattingSystem.use_cases.join_chat_room.JoinChatRoomInputData;

import java.io.IOException;

public interface ShowWeatherInputBoundary {
    void execute() throws IOException;
}
