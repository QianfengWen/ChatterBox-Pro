package chattingSystem.interface_adapter.state;

import chattingSystem.entities.Weather.Weather;

public class GetWeatherState implements ViewState{

    private String weatherInfoDisplay;

    private String cityLocation;

    private String errorMessage = null;

    public GetWeatherState(GetWeatherState copy) {
        weatherInfoDisplay = copy.weatherInfoDisplay;
        cityLocation = copy.cityLocation;
        errorMessage = copy.errorMessage;

    }

    public GetWeatherState() {
        }
    public String getWeatherInfoDisplay() {
        return weatherInfoDisplay;
    }
    public String getCityLocation(){return cityLocation;}

    public void setCityLocation(String cityLocation){this.cityLocation = cityLocation;}

    public void setWeatherInfoDisplay(Weather weatherInfoDisplay) {
        this.weatherInfoDisplay = weatherInfoDisplay.toString();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {return errorMessage;}
}
