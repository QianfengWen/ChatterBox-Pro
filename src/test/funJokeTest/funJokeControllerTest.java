package funJokeTest;

import chattingSystem.use_cases.fun_joke.FunJokeInputBountry;
import chattingSystem.use_cases.fun_joke.FunJokeInputData;
import chattingSystem.interface_adapter.controllers.FunJokeController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class funJokeControllerTest {

    @Test
    public void testExecute() {
        // Arrange
        FunJokeInputBountry mockInteractor = Mockito.mock(FunJokeInputBountry.class);
        FunJokeController controller = new FunJokeController(mockInteractor);

        // Act
        controller.execute();
        // Assert
        verify(mockInteractor, times(1)).execute(any(FunJokeInputData.class));
    }
}
