package chattingSystem.interface_adapter.presenter;

import chattingSystem.frameworks_drivers.api.GetWeatherDataAccessObject;
import chattingSystem.interface_adapter.state.GetWeatherState;
import chattingSystem.interface_adapter.view_models.GetWeatherViewModel;
import chattingSystem.use_cases.get_weather.GetWeatherOutputBoundary;
import chattingSystem.use_cases.get_weather.GetWeatherOutputData;
import chattingSystem.use_cases.show_weather.ShowWeatherOutputBoundary;

import static chattingSystem.app.ShowWeatherFrameFactory.createShowWeatherFrame;


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
