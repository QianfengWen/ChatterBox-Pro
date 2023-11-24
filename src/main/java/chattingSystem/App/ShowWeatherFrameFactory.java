package chattingSystem.App;

import chattingSystem.frameworks_drivers.ui.views.GetWeatherView;
import chattingSystem.frameworks_drivers.ui.views.GetWeatherViewManager;
import chattingSystem.interface_adapter.controllers.GetWeatherController;
import chattingSystem.interface_adapter.presenter.GetWeatherPresenter;
import chattingSystem.interface_adapter.view_models.GetWeatherViewManagerModel;
import chattingSystem.interface_adapter.view_models.GetWeatherViewModel;
import chattingSystem.use_cases.get_weather.GetWeatherDataAccessBoundary;
import chattingSystem.use_cases.get_weather.GetWeatherInputBoundary;
import chattingSystem.use_cases.get_weather.GetWeatherInteractor;
import chattingSystem.use_cases.get_weather.GetWeatherOutputBoundary;


import javax.swing.*;
import java.awt.*;

public class ShowWeatherFrameFactory {
    public static void createShowWeatherFrame(GetWeatherViewModel getWeatherViewModel, GetWeatherDataAccessBoundary getWeatherDataAccessBoundary) {
        JFrame getWeatherFrame = new JFrame("Get Weather");
        getWeatherFrame.setPreferredSize(new Dimension(430, 360));
        getWeatherFrame.setVisible(false);
        GetWeatherController getWeatherController = createGetWeatherUseCase(getWeatherDataAccessBoundary, getWeatherViewModel);
        CardLayout getWeatherCardLayout = new CardLayout();
        JPanel getWeatherViews = new JPanel(getWeatherCardLayout);
        getWeatherFrame.add(getWeatherViews);
        // This keeps track of and manages which view is currently showing.
        GetWeatherViewManagerModel getWeatherViewManagerModel = new GetWeatherViewManagerModel();
        GetWeatherViewManager getWeatherViewManager = new GetWeatherViewManager(getWeatherViews, getWeatherCardLayout, getWeatherViewManagerModel);
        JPanel getWeatherView = new GetWeatherView(getWeatherController, getWeatherViewModel, getWeatherViewManagerModel, getWeatherViewManager);
        getWeatherFrame.setContentPane(getWeatherView);
        getWeatherFrame.pack();
        getWeatherFrame.setVisible(true);
        getWeatherViewModel.setCurrentFrame(getWeatherFrame);
    }
    public static GetWeatherController createGetWeatherUseCase(
           GetWeatherDataAccessBoundary getWeatherDataAccessBoundary, GetWeatherViewModel getWeatherViewModel
    ) {

        // Notice how we pass this method's parameters to the Presenter.
        GetWeatherOutputBoundary getWeatherOutputBoundary = new GetWeatherPresenter(getWeatherViewModel);

        GetWeatherInputBoundary getWeatherInteractor = new GetWeatherInteractor(getWeatherDataAccessBoundary, getWeatherOutputBoundary);

        return new GetWeatherController(getWeatherInteractor);
    }
}

