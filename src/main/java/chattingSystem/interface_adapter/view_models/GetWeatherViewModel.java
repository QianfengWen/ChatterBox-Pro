package main.java.chattingSystem.interface_adapter.view_models;

import main.java.chattingSystem.interface_adapter.state.GetWeatherState;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetWeatherViewModel extends ViewModel{
    public String TITLE_LABEL = "Weather Report";
    private JFrame currentFrame;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private GetWeatherState state = new GetWeatherState();

    public GetWeatherViewModel() {
        super("Get Weather");
    }
    public JFrame getCurrentFrame() {
        return currentFrame;
    }
    public void setCurrentFrame(JFrame currentFrame){
        this.currentFrame = currentFrame;
    }
    public GetWeatherState getState() {return state;}

    public void setState(GetWeatherState getWeatherState) {
        this.state = getWeatherState;
    }

    @Override
    public String getViewName() {
        return TITLE_LABEL;
    }

    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
