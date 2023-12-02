package getWeatherTest;

import chattingSystem.interface_adapter.state.GetWeatherState;
import chattingSystem.interface_adapter.view_models.GetWeatherViewModel;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class getWeatherViewModelTest {

    @Test
    public void testConstructorAndViewName() {
        // Arrange
        GetWeatherViewModel getWeatherViewModel = new GetWeatherViewModel();

        // Act & Assert
        assertEquals("Weather Report", getWeatherViewModel.getViewName());
    }

    @Test
    public void testGetCurrentFrameAndSetCurrentFrame() {
        // Arrange
        GetWeatherViewModel viewModel = new GetWeatherViewModel();
        JFrame frame = new JFrame();

        // Act
        viewModel.setCurrentFrame(frame);

        // Assert
        assertEquals(frame, viewModel.getCurrentFrame());
    }

    @Test
    public void testGetStateAndSetState() {
        // Arrange
        GetWeatherViewModel viewModel = new GetWeatherViewModel();
        GetWeatherState weatherState = new GetWeatherState();

        // Act
        viewModel.setState(weatherState);

        // Assert
        assertEquals(weatherState, viewModel.getState());
    }

    @Test
    public void testFirePropertyChanged() {
        // Arrange
        GetWeatherViewModel getWeatherViewModel = new GetWeatherViewModel();

        // Use a lambda expression for simplicity
        getWeatherViewModel.addPropertyChangeListener(evt ->
                assertEquals("state", evt.getPropertyName(),
                        "Property name should be 'state'"));
        getWeatherViewModel.firePropertyChanged();

    }

//    @Test
//    public void testAddPropertyChangeListener() {
//        // Arrange
//        GetWeatherViewModel viewModel = new GetWeatherViewModel();
//        MockPropertyChangeListener listener = new MockPropertyChangeListener();
//
//        // Act
//        viewModel.addPropertyChangeListener(listener);
//
//        // Assert
//        assertTrue(viewModel.getPropertyChangeSupport().hasListeners("state"));
//    }
//
//    private static class MockPropertyChangeListener implements PropertyChangeListener {
//        private int numPropertyChangeEvents = 0;
//        private PropertyChangeEvent lastPropertyChangeEvent;
}

