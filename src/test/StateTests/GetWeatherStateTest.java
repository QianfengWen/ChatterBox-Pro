package StateTests;

import chattingSystem.entities.Weather.Weather;
import chattingSystem.interface_adapter.state.GetWeatherState;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GetWeatherStateTest {

    @Test
    void getAndSetWeatherInfoDisplayTest() {
        GetWeatherState getWeatherState = new GetWeatherState();
        Weather weather = new Weather("London", "3:40", 30, "sunny", 30, "NW", 50, 2.5F);
        getWeatherState.setWeatherInfoDisplay(weather);
        assertEquals(weather.toString(), getWeatherState.getWeatherInfoDisplay());
    }


    @Test
    void getAndSetCityLocationTest() {
        GetWeatherState getWeatherState = new GetWeatherState();
        getWeatherState.setCityLocation("New York");
        String result = getWeatherState.getCityLocation();
        assertEquals("New York", result);
    }

    @Test
    void getAndSetErrorMessage() {
        GetWeatherState getWeatherState = new GetWeatherState();
        getWeatherState.setErrorMessage("Invalid city");
        assertEquals("Invalid city", getWeatherState.getErrorMessage());
    }

    @Test
    void copyConstructorTest() {
        GetWeatherState original = new GetWeatherState();
        original.setWeatherInfoDisplay(new Weather("London", "3:40", 30, "sunny", 30, "NW", 50, 2.5F));
        original.setCityLocation("London");
        GetWeatherState copy = new GetWeatherState(original);
        assertEquals(original.getWeatherInfoDisplay(), copy.getWeatherInfoDisplay());
        assertEquals(original.getCityLocation(), copy.getCityLocation());
        assertEquals(original.getErrorMessage(), copy.getErrorMessage());
    }
}
