package showWeatherTest;

import chattingSystem.use_cases.show_weather.ShowWeatherInteractor;
import chattingSystem.use_cases.show_weather.ShowWeatherOutputBoundary;
import org.junit.Test;

public class showWeatherInteractorTest {

    @Test
    public void testWeatherInteractorTest() {
        final boolean[] runSuccess = {false};
        ShowWeatherOutputBoundary showWeatherOutputBoundary = new ShowWeatherOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                runSuccess[0] = true;
            }

            @Override
            public void prepareFailView() {

            }
        };
        ShowWeatherInteractor showWeatherInteractor = new ShowWeatherInteractor(showWeatherOutputBoundary);
        showWeatherInteractor.execute();
    }
}
