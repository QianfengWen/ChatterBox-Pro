package getWeatherTest;

import chattingSystem.entities.Weather.Weather;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class getWeatherEntityTest {

    @Test
    public void testWeatherBuilder() {
        String cityLocation = "New York";
        String lastUpdated = "2023-01-01 12:00:00";
        int temperature = (int) 25.5f;
        String condition = "Sunny";
        int windDegree = 180;
        String windDirection = "South";
        int humidity = 70;
        float pm = 10.5f;

        Weather weather = Weather.builder()
                .cityLocation(cityLocation)
                .lastUpdated(lastUpdated)
                .temperature(temperature)
                .condition(condition)
                .windDegree(windDegree)
                .windDirection(windDirection)
                .humidity(humidity)
                .pm(pm)
                .build();

        Weather exceptWeather = new Weather(cityLocation, lastUpdated, temperature, condition,
                                            windDegree, windDirection, humidity,pm);

        assertEquals(weather.toString(), exceptWeather.toString());
    }

    @Test
    public void testWeatherToString() {
        String cityLocation = "Paris";
        String lastUpdated = "2023-01-01 15:30:00";
        float temperature = 20.0f;
        String condition = "Cloudy";
        int windDegree = 270;
        String windDirection = "West";
        int humidity = 80;
        float pm = 15.2f;

        Weather weather = Weather.builder()
                .cityLocation(cityLocation)
                .lastUpdated(lastUpdated)
                .temperature((int) temperature)
                .condition(condition)
                .windDegree(windDegree)
                .windDirection(windDirection)
                .humidity(humidity)
                .pm(pm)
                .build();

        String expectedToString = "Location: " + cityLocation + "\n" +
                "Last updated time: " + lastUpdated + "\n" +
                "Temperature in Celsius: " + temperature + "\n" +
                "Weather Condition: " + condition + "\n" +
                "Wind Degree: " + windDegree + "\n" +
                "Wind direction: " + windDirection + "\n" +
                "Humidity: " + humidity + "\n" +
                "PM 2.5: " + pm + "\n";

        assertEquals(expectedToString, weather.toString());
    }
}
