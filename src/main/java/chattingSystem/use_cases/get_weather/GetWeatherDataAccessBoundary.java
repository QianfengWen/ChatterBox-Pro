package main.java.chattingSystem.use_cases.get_weather;

import main.java.chattingSystem.entities.Weather.Weather;

public interface GetWeatherDataAccessBoundary {
    Weather getWeather(String cityLocation);
    public boolean WeatherDataIsValid(String cityLocation);
    }


