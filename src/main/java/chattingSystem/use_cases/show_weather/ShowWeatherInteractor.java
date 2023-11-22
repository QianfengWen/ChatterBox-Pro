package main.java.chattingSystem.use_cases.show_weather;

import main.java.chattingSystem.use_cases.join_chat_room.JoinChatRoomInputData;

import java.io.IOException;

public class ShowWeatherInteractor implements ShowWeatherInputBoundary{
    private ShowWeatherOutputBoundary showWeatherOutputBoundary;
    public ShowWeatherInteractor(ShowWeatherOutputBoundary showWeatherOutputBoundary) {
        this.showWeatherOutputBoundary = showWeatherOutputBoundary;
    }
    @Override
    public void execute()  {
        showWeatherOutputBoundary.prepareSuccessView();
    }
}
