package main.java.chattingSystem.use_cases.get_weather;

public interface GetWeatherOutputBoundary {

    void prepareSuccessView(GetWeatherOutputData getWeatherOutputData);
    void prepareFailView(String errorMessage);

}
