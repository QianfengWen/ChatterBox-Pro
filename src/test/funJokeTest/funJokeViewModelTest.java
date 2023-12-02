package funJokeTest;

import chattingSystem.interface_adapter.state.FunJokeState;
import chattingSystem.interface_adapter.view_models.FunJokeViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class funJokeViewModelTest {

    private FunJokeViewModel funJokeViewModel;

    private PropertyChangeListener listener;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        funJokeViewModel = new FunJokeViewModel();
    }

    @Test
    public void testGetSetTitleLabel() {
        assertEquals("Funny Joke!!", funJokeViewModel.TITLE_LABEL);
    }

    @Test
    public void testGetCurrentFrame() {
        assertNull(funJokeViewModel.getCurrentFrame());
    }

    @Test
    public void testSetCurrentFrame() {
        JFrame testFrame = new JFrame("Test Frame");
        funJokeViewModel.setCurrentFrame(testFrame);
        assertEquals(testFrame, funJokeViewModel.getCurrentFrame());
    }

    @Test
    public void testGetSetState() {
        FunJokeState testState = new FunJokeState();
        funJokeViewModel.setState(testState);
        assertEquals(testState, funJokeViewModel.getState());
    }

    @Test
    public void testGetViewName() {
        assertEquals("Funny Joke!!", funJokeViewModel.getViewName());
    }

    @Test
    public void testFirePropertyChanged() {
        FunJokeViewModel funJokeViewModel = new FunJokeViewModel();

        // Use a lambda expression for simplicity
        funJokeViewModel.addPropertyChangeListener(evt ->
                assertEquals("state", evt.getPropertyName(),
                        "Property name should be 'state'"));
        funJokeViewModel.firePropertyChanged();
        // To be completed based on the expected behavior of your implementation
    }

//    @Test
//    public void testAddPropertyChangeListener() {
//        FunJokeViewModel funJokeViewModel = new FunJokeViewModel();
//        // Act
//        funJokeViewModel.addPropertyChangeListener(evt ->
//                assertEquals("state", evt.getPropertyName(),
//                        "Property name should be 'state'"));
//
//        // Assert
//        assertTrue(funJokeViewModel.getPropertyChangeSupport().hasListeners("state"),
//                "Listeners should be added for property 'state'");
//        // To be completed based on the expected behavior of your implementation
//    }


}

