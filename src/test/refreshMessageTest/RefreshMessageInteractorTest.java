package refreshMessageTest;

import chattingSystem.use_cases.refresh_messages.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class RefreshMessageInteractorTest {

    @Test
    void testExecute() {
        // Mock dependencies
        RefreshMessagesDataAccessBoundary mockDataAccessBoundary = mock(RefreshMessagesDataAccessBoundary.class);
        RefreshMessagesOutputBoundary mockOutputBoundary = mock(RefreshMessagesOutputBoundary.class);

        // Define behavior for mockDataAccessBoundary
        List<String> mockMessages = Arrays.asList("Message 1", "Message 2");
        when(mockDataAccessBoundary.fetchAllMessages()).thenReturn(mockMessages);

        // Create instance of RefreshMessageInteractor
        RefreshMessageInteractor interactor = new RefreshMessageInteractor(mockDataAccessBoundary, mockOutputBoundary);

        // Execute the method to test
        interactor.execute();

        // Verify that refreshSuccessView is called with the correct data
        // Verify that refreshSuccessView is called with an object that has the same data
        verify(mockOutputBoundary).refreshSuccessView(argThat(new ArgumentMatcher<RefreshMessageOutputData>() {
            @Override
            public boolean matches(RefreshMessageOutputData argument) {
                return argument.getMessags().equals(mockMessages);
            }
        }));
    }

    @Test
    void testInputData(){
        RefreshMessageInputData inputData = new RefreshMessageInputData();
    }
}
