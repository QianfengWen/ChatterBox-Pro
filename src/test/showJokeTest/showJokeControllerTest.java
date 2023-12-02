package showJokeTest;

import chattingSystem.use_cases.show_joke.ShowJokeInputBoundry;
import chattingSystem.interface_adapter.controllers.ShowJokeController;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class showJokeControllerTest {

    @Test
    public void testExecute() {
        // Arrange
        ShowJokeInputBoundry mockInteractor = new MockShowJokeInputBoundary(true);
        ShowJokeController controller = new ShowJokeController(mockInteractor);

        // Act & Assert
        assertThrows(IOException.class, () -> controller.execute(),
                "execute should propagate IOException from the interactor");
    }

    private static class MockShowJokeInputBoundary implements ShowJokeInputBoundry {
        private final boolean shouldThrowIOException;

        public MockShowJokeInputBoundary(boolean shouldThrowIOException) {
            this.shouldThrowIOException = shouldThrowIOException;
        }

        @Override
        public void execute() throws IOException {
            if (shouldThrowIOException) {
                throw new IOException("Mocked IOException");
            }
        }
    }
}

