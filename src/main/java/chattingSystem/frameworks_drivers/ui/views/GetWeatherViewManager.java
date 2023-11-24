package chattingSystem.frameworks_drivers.ui.views;

import chattingSystem.interface_adapter.view_models.ChatRoomViewManagerModel;
import chattingSystem.interface_adapter.view_models.GetWeatherViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GetWeatherViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel getWeatherViews;
    private GetWeatherViewManagerModel getWeatherViewManagerModel;

    public GetWeatherViewManager(JPanel getWeatherViews, CardLayout cardLayout, GetWeatherViewManagerModel getWeatherViewManagerModel) {
        this.getWeatherViews = getWeatherViews;
        this.cardLayout = cardLayout;
        this.getWeatherViewManagerModel = getWeatherViewManagerModel;
        this.getWeatherViewManagerModel.addPropertyChangeListener(this);
    }
    public void addView(JPanel view, String viewnName) {
        getWeatherViews.add(view, viewnName);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
            cardLayout.show(getWeatherViews, viewModelName);
        }
    }
}
