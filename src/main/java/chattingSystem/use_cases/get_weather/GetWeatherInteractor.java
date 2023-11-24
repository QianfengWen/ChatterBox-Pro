package chattingSystem.use_cases.get_weather;
import chattingSystem.entities.Weather.Weather;

public class GetWeatherInteractor implements GetWeatherInputBoundary{
    final GetWeatherDataAccessBoundary getWeatherDataAccessObject;
    final GetWeatherOutputBoundary getWeatherPresenter;

    public GetWeatherInteractor(GetWeatherDataAccessBoundary getWeatherDataAccessBoundary, GetWeatherOutputBoundary getWeatherOutputBoundary) {
        this.getWeatherDataAccessObject = getWeatherDataAccessBoundary;
        this.getWeatherPresenter = getWeatherOutputBoundary;
    }

    @Override
    public void execute(GetWeatherInputData getWeatherInputData) {
        if (getWeatherDataAccessObject.WeatherDataIsValid(getWeatherInputData.getCityLocation())) {
            Weather weatherInfo = getWeatherDataAccessObject.getWeather(getWeatherInputData.getCityLocation());
            GetWeatherOutputData getWeatherOutputData = new GetWeatherOutputData(weatherInfo);
            getWeatherPresenter.prepareSuccessView(getWeatherOutputData);
        }
        else {
            String cityLocation = getWeatherInputData.getCityLocation();
            getWeatherPresenter.prepareFailView("Unable to retrieve current weather conditions for " + cityLocation);
        }
    }

}
