package main.java.chattingSystem.interface_adapter.controllers;

import main.java.chattingSystem.use_cases.get_weather.GetWeatherInputBoundary;
import main.java.chattingSystem.use_cases.get_weather.GetWeatherInputData;


public class GetWeatherController {
    final GetWeatherInputBoundary getWeatherInteractor;
    public GetWeatherController(GetWeatherInputBoundary getWeatherInteractor) {
        this.getWeatherInteractor = getWeatherInteractor;
    }

    public void execute(String cityLocation) {
        GetWeatherInputData getWeatherInputData = new GetWeatherInputData (cityLocation);
        getWeatherInteractor.execute(getWeatherInputData);
    }
}
