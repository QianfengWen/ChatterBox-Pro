package showWeatherTest;

import chattingSystem.use_cases.show_weather.ShowWeatherInputBoundary;
import chattingSystem.interface_adapter.controllers.ShowWeatherController;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class showWeatherControllerTest {

    @Test
    public void testExecute() {
        // Arrange
        ShowWeatherInputBoundary mockInteractor = new MockShowWeatherInputBoundary();
        ShowWeatherController controller = new ShowWeatherController(mockInteractor);

        // Act & Assert
        assertThrows(IOException.class, controller::execute,
                "execute should propagate IOException from the interactor");

    }

    private static class MockShowWeatherInputBoundary implements ShowWeatherInputBoundary {
        @Override
        public void execute() throws IOException {
            throw new IOException("Mocked IOException");
            // Mocked behavior without throwing IOException
        }
    }
}

