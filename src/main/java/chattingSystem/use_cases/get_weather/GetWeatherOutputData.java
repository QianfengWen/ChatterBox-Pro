package chattingSystem.use_cases.get_weather;

import chattingSystem.entities.Weather.Weather;

public class GetWeatherOutputData {
    private Weather weatherInfo;
    public GetWeatherOutputData(Weather weatherInfo) {
        this.weatherInfo = weatherInfo;
    }
    public Weather getWeatherInfo() {
        return weatherInfo;
    }
}
