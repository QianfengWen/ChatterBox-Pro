package chattingSystem.interface_adapter.controllers;


import chattingSystem.use_cases.show_weather.ShowWeatherInputBoundary;

import java.io.IOException;

public class ShowWeatherController {
    final ShowWeatherInputBoundary showWeatherInteractor;
    public ShowWeatherController(ShowWeatherInputBoundary showWeatherInteractor) {
        this.showWeatherInteractor = showWeatherInteractor;
    }

    public void execute() throws IOException {
        showWeatherInteractor.execute();
    }
}
