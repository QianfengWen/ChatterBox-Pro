package chattingSystem.use_cases.get_weather;

public class GetWeatherInputData {
    private String cityLocation;
    public GetWeatherInputData(String cityLocation) {
        this.cityLocation = cityLocation;
    }
    public String getCityLocation(){
        return cityLocation;
    }
}
