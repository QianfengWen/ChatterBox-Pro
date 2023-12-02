package getWeatherTest;
import chattingSystem.entities.Weather.Weather;
import chattingSystem.interface_adapter.presenter.GetWeatherPresenter;
import chattingSystem.interface_adapter.state.GetWeatherState;
import chattingSystem.interface_adapter.view_models.GetWeatherViewModel;
import chattingSystem.use_cases.get_weather.GetWeatherOutputData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getWeatherPresenterTest {

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        GetWeatherViewModel mockViewModel = new GetWeatherViewModel();
        GetWeatherPresenter presenter = new GetWeatherPresenter(mockViewModel);
        GetWeatherOutputData mockOutputData = new GetWeatherOutputData(new Weather("New York", "2023-01-01 12:00:00", (int) 25.5f, "Sunny",
                180, "South", 70, 10.5f));

        String expectedInfo = "Location: " + "New York" + "\n" +
                "Last updated time: " + "2023-01-01 12:00:00" + "\n" +
                "Temperature in Celsius: " + Float.toString((int) 25.5f) + "\n" +
                "Weather Condition: " + "Sunny" + "\n" +
                "Wind Degree: " + Integer.toString(180) + "\n" +
                "Wind direction: " + "South" + "\n" +
                "Humidity: " + Integer.toString(70) + "\n" +
                "PM 2.5: " + Float.toString(10.5f) + "\n";
        // Act
        presenter.prepareSuccessView(mockOutputData);

        // Assert
        GetWeatherState state = mockViewModel.getState();
        assertEquals(expectedInfo, state.getWeatherInfoDisplay());
        assertEquals(null, state.getErrorMessage());
    }

    @Test
    public void testPrepareFailView() {
        // Arrange
        GetWeatherViewModel mockViewModel = new GetWeatherViewModel();
        GetWeatherPresenter presenter = new GetWeatherPresenter(mockViewModel);

        // Act
        presenter.prepareFailView("Error Message");

        // Assert
        GetWeatherState state = mockViewModel.getState();
        assertEquals("Error Message", state.getErrorMessage());
        // Ensure other fields are not affected
        assertEquals(null, state.getWeatherInfoDisplay());
    }

    @Test
    public void testPrepareSuccessViewForShowWeather() {
        // Arrange
        GetWeatherViewModel mockViewModel = new GetWeatherViewModel();
        GetWeatherPresenter presenter = new GetWeatherPresenter(mockViewModel);

        // Act
        presenter.prepareSuccessView();

        // Assert
        // Assuming the creation of ShowWeatherFrame involves some side effects, you may check them here
        // For simplicity, we're not asserting specific behavior here
    }

    @Test
    public void testPrepareFailViewForShowWeather() {
        // Arrange
        GetWeatherViewModel mockViewModel = new GetWeatherViewModel();
        GetWeatherPresenter presenter = new GetWeatherPresenter(mockViewModel);

        // Act
        presenter.prepareFailView();

        // Assert
        // Ensure that the ViewModel state is not affected
        GetWeatherState state = mockViewModel.getState();
        assertEquals(null, state.getWeatherInfoDisplay());
        assertEquals(null, state.getErrorMessage());
    }
}
