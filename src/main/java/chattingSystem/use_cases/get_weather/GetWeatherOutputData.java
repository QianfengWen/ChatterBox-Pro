package main.java.chattingSystem.use_cases.get_weather;

import main.java.chattingSystem.entities.Weather.Weather;

public class GetWeatherOutputData {
    private Weather weatherInfo;
    public GetWeatherOutputData(Weather weatherInfo) {
        this.weatherInfo = weatherInfo;
    }
    public Weather getWeatherInfo() {
        return weatherInfo;
    }
}
