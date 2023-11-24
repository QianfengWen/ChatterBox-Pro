package chattingSystem.use_cases.get_weather;

import chattingSystem.entities.Weather.Weather;

public interface GetWeatherDataAccessBoundary {
    Weather getWeather(String cityLocation);
    public boolean WeatherDataIsValid(String cityLocation);
    }


