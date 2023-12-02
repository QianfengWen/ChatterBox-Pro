package getWeatherTest;

import chattingSystem.entities.Weather.Weather;
import chattingSystem.interface_adapter.state.GetWeatherState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class getWeatherStateTest {

    @Test
    public void testConstructorWithCopy() {
        // Arrange
        GetWeatherState originalState = new GetWeatherState();
        originalState.setCityLocation("New York");
        originalState.setWeatherInfoDisplay(new Weather("New York", "2023-01-01 12:00:00", (int) 25.5f, "Sunny",
                180, "South", 70, 10.5f));
        originalState.setErrorMessage("OriginalError");
        System.out.println(originalState.getErrorMessage());

        // Act
        GetWeatherState copyState = new GetWeatherState(originalState);
        System.out.println(copyState.getErrorMessage());

        // Assert
        assertNotSame(originalState, copyState);
        assertEquals(originalState.getCityLocation(), copyState.getCityLocation());
        assertEquals(originalState.getWeatherInfoDisplay(), copyState.getWeatherInfoDisplay());
        assertEquals(originalState.getErrorMessage(), copyState.getErrorMessage());
    }

    @Test
    public void testGetterAndSetter() {
        // Arrange
        GetWeatherState state = new GetWeatherState();
        state.setCityLocation("New York");
        state.setWeatherInfoDisplay(new Weather("New York", "2023-01-01 12:00:00", (int) 25.5f, "Sunny",
                180, "South", 70, 10.5f));
        state.setErrorMessage("TestError");
        String expectedInfo = "Location: " + "New York" + "\n" +
                "Last updated time: " + "2023-01-01 12:00:00" + "\n" +
                "Temperature in Celsius: " + Float.toString((int) 25.5f) + "\n" +
                "Weather Condition: " + "Sunny" + "\n" +
                "Wind Degree: " + Integer.toString(180) + "\n" +
                "Wind direction: " + "South" + "\n" +
                "Humidity: " + Integer.toString(70) + "\n" +
                "PM 2.5: " + Float.toString(10.5f) + "\n";

        // Act & Assert
        assertEquals("New York", state.getCityLocation());
        assertEquals(expectedInfo, state.getWeatherInfoDisplay().toString());
        assertEquals("TestError", state.getErrorMessage());
    }

    @Test
    public void testSetWeatherInfoDisplay() {
        // Arrange
        GetWeatherState state = new GetWeatherState();
        Weather weather = new Weather("New York", "2023-01-01 12:00:00", (int) 25.5f, "Sunny",
                180, "South", 70, 10.5f);

        String expectedInfo = "Location: " + "New York" + "\n" +
                "Last updated time: " + "2023-01-01 12:00:00" + "\n" +
                "Temperature in Celsius: " + Float.toString((int) 25.5f) + "\n" +
                "Weather Condition: " + "Sunny" + "\n" +
                "Wind Degree: " + Integer.toString(180) + "\n" +
                "Wind direction: " + "South" + "\n" +
                "Humidity: " + Integer.toString(70) + "\n" +
                "PM 2.5: " + Float.toString(10.5f) + "\n";

        // Act
        state.setWeatherInfoDisplay(weather);

        // Assert
        assertEquals(expectedInfo, state.getWeatherInfoDisplay().toString());
    }
}
