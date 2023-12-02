package getWeatherTest;

import chattingSystem.use_cases.get_weather.GetWeatherInputBoundary;
import chattingSystem.use_cases.get_weather.GetWeatherInputData;
import chattingSystem.interface_adapter.controllers.GetWeatherController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class getWeatherControllerTest {

    @Test
    public void testExecute() {
        // Arrange
        GetWeatherInputBoundary mockInteractor = Mockito.mock(GetWeatherInputBoundary.class);
        GetWeatherController controller = new GetWeatherController(mockInteractor);
        String cityLocation = "New York";

        // Act
        controller.execute(cityLocation);

        // Assert
        Mockito.verify(mockInteractor, Mockito.times(1)).execute(Mockito.any(GetWeatherInputData.class));



    }
}

