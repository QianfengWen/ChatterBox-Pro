package main.java.chattingSystem.interface_adapter.view_models;

import main.java.chattingSystem.interface_adapter.state.ViewState;

import java.beans.PropertyChangeListener;

public abstract class ViewModel {
    // Abstract methods that can be overridden by concrete ViewModel classes
    private final String viewName;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() {
        return this.viewName;
    }

    public abstract void firePropertyChanged();
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);

}

