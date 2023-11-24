package chattingSystem.use_cases.show_weather;

import chattingSystem.use_cases.get_weather.GetWeatherDataAccessBoundary;

public interface ShowWeatherOutputBoundary {
    void prepareSuccessView();
    void prepareFailView();
}
