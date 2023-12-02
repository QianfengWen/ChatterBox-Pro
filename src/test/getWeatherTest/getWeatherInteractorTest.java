package getWeatherTest;

import chattingSystem.entities.FunJoke.FunJoke;
import chattingSystem.entities.Weather.Weather;
import chattingSystem.use_cases.fun_joke.*;
import chattingSystem.use_cases.get_weather.*;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class getWeatherInteractorTest {

    @Test
    public void testGenerateWeatherSuccessfulInteractor() {
//        FunJokeDataAccessBoundary funJokeDataAccessObject = new FunJokeDataAccessObject();
        GetWeatherDataAccessBoundary getWeatherDataAccessBoundary = new GetWeatherDataAccessBoundary() {
            @Override
            public Weather getWeather(String cityLocation) {
                return new Weather("New York", "2023-01-01 12:00:00", (int) 25.5f, "Sunny",
                        180, "South", 70, 10.5f);
            }

            @Override
            public boolean WeatherDataIsValid(String cityLocation) {
                return true;
            }
        };
            GetWeatherOutputBoundary getWeatherPresenter= new GetWeatherOutputBoundary() {
                @Override
                public void prepareSuccessView(GetWeatherOutputData getWeatherOutputData) {
                    assertTrue(getWeatherDataAccessBoundary.WeatherDataIsValid("New York"));
                    assertEquals(getWeatherOutputData.getWeatherInfo().toString(), getWeatherDataAccessBoundary.getWeather("New York").toString());
                }

                @Override
                public void prepareFailView(String errorMessage) {

                }};
            GetWeatherInputData getWeatherInputData = new GetWeatherInputData("New York");
            GetWeatherInteractor getWeatherInteractor =  new GetWeatherInteractor(getWeatherDataAccessBoundary,
                    getWeatherPresenter );
            getWeatherInteractor.execute(getWeatherInputData);
    }


    @Test
    public void testGenerateWeatherFailViewInteractor() {
        GetWeatherDataAccessBoundary getWeatherDataAccessBoundary1 = new GetWeatherDataAccessBoundary(){
            @Override
            public Weather getWeather(String cityLocation) {
                throw new RuntimeException("Cannot Get Weather Report For Typed city");
            }

            @Override
            public boolean WeatherDataIsValid(String cityLocation) {
                return false;
            }

        };
        final boolean[] prepareFailViewcalled = {false};
        GetWeatherOutputBoundary getWeatherPresenter1= new GetWeatherOutputBoundary() {
            @Override
            public void prepareSuccessView(GetWeatherOutputData getWeatherOutputData) {

            }

            @Override
            public void prepareFailView(String errorMessage) {
                prepareFailViewcalled[0] = true;
            }
        };
        GetWeatherInteractor getWeatherInteractor1 = new GetWeatherInteractor(getWeatherDataAccessBoundary1, getWeatherPresenter1);
        GetWeatherInputData getWeatherInputData = new GetWeatherInputData("asskdjf");
        getWeatherInteractor1.execute(getWeatherInputData);
        assertTrue(prepareFailViewcalled[0]);
    }

    @org.junit.jupiter.api.Test
    void testWeatherInPutDataSetCityLocation() {
        // Arrange
        String cityLocation = "New York";

        // Act
        GetWeatherInputData inputData = new GetWeatherInputData(cityLocation);

        // Assert
        assertEquals(cityLocation, inputData.getCityLocation());
    }

    @org.junit.jupiter.api.Test
    void testWeatherInPutDataGetCityLocation() {
        // Arrange
        String cityLocation = "New York";
        GetWeatherInputData inputData = new GetWeatherInputData(cityLocation);

        // Act
        String result = inputData.getCityLocation();

        // Assert
        assertEquals(cityLocation, result);
    }

    @org.junit.jupiter.api.Test
    void testWeatherOutPutDataSetWeatherInfo() {
        // Arrange
        Weather weatherInfo = new Weather("New York", "2023-01-01 12:00:00", (int) 25.5f, "Sunny",
                180, "South", 70, 10.5f);

        // Act
        GetWeatherOutputData outputData = new GetWeatherOutputData(weatherInfo);

        // Assert
        // Directly inspect the state of the outputData object
        assertNotNull(outputData);
        assertEquals(weatherInfo, outputData.getWeatherInfo());
    }

    @org.junit.jupiter.api.Test
    void testWeatherOutPutDataGetWeatherInfo() {
        // Arrange
        Weather weatherInfo = new Weather("New York", "2023-01-01 12:00:00", (int) 25.5f, "Sunny",
                180, "South", 70, 10.5f);
        // Act
        GetWeatherOutputData outputData = new GetWeatherOutputData(weatherInfo);

        // Act
        Weather result = outputData.getWeatherInfo();

        // Assert
        assertEquals(weatherInfo, result);
    }

}

