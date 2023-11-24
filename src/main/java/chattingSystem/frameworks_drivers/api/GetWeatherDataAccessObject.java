package chattingSystem.frameworks_drivers.api;

import chattingSystem.entities.Weather.Weather;
import chattingSystem.use_cases.get_weather.GetWeatherDataAccessBoundary;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GetWeatherDataAccessObject implements GetWeatherDataAccessBoundary {
    private static final String WEATHER_API_URL = "http://api.weatherapi.com/v1/current.json?key=54ecd02a4a6d4d349fb195245231711&q=London&aqi=yes";
    private static final String API_KEY = "54ecd02a4a6d4d349fb195245231711";

    public GetWeatherDataAccessObject(){
    }

    @Override
    public Weather getWeather(String cityLocation) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("http://api.weatherapi.com/v1/current.json?key=%s&q=%s&aqi=yes", API_KEY, cityLocation))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONObject weatherloc = responseBody.getJSONObject("location");
                JSONObject weather = responseBody.getJSONObject("current");
                return Weather.builder()
                        .cityLocation(weatherloc.getString("name"))
                        .lastUpdated(weather.getString("last_updated"))
                        .temperature(weather.getInt("temp_c"))
                        .condition(weather.getJSONObject("condition").getString("text"))
                        .windDegree(weather.getInt("wind_degree"))
                        .windDirection(weather.getString("wind_dir"))
                        .humidity(weather.getInt("humidity"))
                        .pm(weather.getJSONObject("air_quality").getInt("pm2_5"))
                        .build();
            } else {
//
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean WeatherDataIsValid(String cityLocation) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("http://api.weatherapi.com/v1/current.json?key=%s&q=%s&aqi=yes", API_KEY, cityLocation))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                return true;
            } else {
                return false;
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}