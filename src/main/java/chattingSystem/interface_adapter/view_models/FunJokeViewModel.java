package chattingSystem.interface_adapter.view_models;


import chattingSystem.interface_adapter.state.FunJokeState;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FunJokeViewModel extends ViewModel{

    public String TITLE_LABEL = "Funny Joke!!";
    private JFrame currentFrame;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private FunJokeState state = new FunJokeState();

    public FunJokeViewModel() {
        super("Have Funny Joke");
    }

    public JFrame getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(JFrame currentFrame){
        this.currentFrame = currentFrame;
    }

    public FunJokeState getState() {return state;}

    public void setState(FunJokeState funJokeState) {
        this.state = funJokeState;
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
