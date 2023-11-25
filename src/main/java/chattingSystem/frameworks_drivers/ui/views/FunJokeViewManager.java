package chattingSystem.frameworks_drivers.ui.views;

import chattingSystem.interface_adapter.view_models.FunJokeViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FunJokeViewManager implements PropertyChangeListener {

    private final CardLayout cardLayout;

    private final JPanel funJokeViews;

    private FunJokeViewManagerModel funJokeViewManagerModel;

    public FunJokeViewManager(JPanel funJokeViews, CardLayout cardLayout, FunJokeViewManagerModel funJokeViewManagerModel) {
        this.funJokeViews = funJokeViews;
        this.cardLayout = cardLayout;
        this.funJokeViewManagerModel = funJokeViewManagerModel;
        this.funJokeViewManagerModel.addPropertyChangeListener(this);
    }
    public void addView(JPanel view, String viewnName) {
        funJokeViews.add(view, viewnName);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
            cardLayout.show(funJokeViews, viewModelName);
        }
    }
}
