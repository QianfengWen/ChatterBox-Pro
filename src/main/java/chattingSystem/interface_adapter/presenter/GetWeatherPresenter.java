package main.java.chattingSystem.interface_adapter.presenter;

import main.java.chattingSystem.frameworks_drivers.api.GetWeatherDataAccessObject;
import main.java.chattingSystem.interface_adapter.state.GetWeatherState;
import main.java.chattingSystem.interface_adapter.view_models.GetWeatherViewModel;
import main.java.chattingSystem.use_cases.get_weather.GetWeatherOutputBoundary;
import main.java.chattingSystem.use_cases.get_weather.GetWeatherOutputData;
import main.java.chattingSystem.use_cases.show_weather.ShowWeatherOutputBoundary;

import static main.java.chattingSystem.App.ShowWeatherFrameFactory.createShowWeatherFrame;


public class GetWeatherPresenter implements GetWeatherOutputBoundary, ShowWeatherOutputBoundary {

    private final GetWeatherViewModel getWeatherViewModel;
    public GetWeatherPresenter(GetWeatherViewModel getWeatherViewModel) {
        this.getWeatherViewModel = getWeatherViewModel;
    }

    @Override
    public void prepareSuccessView(GetWeatherOutputData weatherInfo) {
        GetWeatherState currentState = getWeatherViewModel.getState();
        currentState.setWeatherInfoDisplay(weatherInfo.getWeatherInfo());
        getWeatherViewModel.setState(currentState);
        getWeatherViewModel.firePropertyChanged();
    }
    @Override
    public void prepareFailView(String error) {
        GetWeatherState currentState = getWeatherViewModel.getState();
        currentState.setErrorMessage(error);
        getWeatherViewModel.firePropertyChanged();
    }


    @Override
    public void prepareSuccessView() {
        GetWeatherDataAccessObject getWeatherDataAccessBoundary = new GetWeatherDataAccessObject();
        createShowWeatherFrame(getWeatherViewModel, getWeatherDataAccessBoundary);


    }

    @Override
    public void prepareFailView() {

    }
}
