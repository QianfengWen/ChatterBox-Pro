package logoutTest;

import chattingSystem.entities.User.User;
import chattingSystem.use_cases.log_out.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class LogOutInteractorTest {

    @Test
    void testSuccessfulLogOut() {
        // Mock dependencies
        LogOutOutputBoundary mockOutputBoundary = mock(LogOutOutputBoundary.class);
        LogOutDataAccessBoundary mockDataAccessBoundary = mock(LogOutDataAccessBoundary.class);

        // Create instance of LogOutInteractor
        LogOutInteractor interactor = new LogOutInteractor(mockOutputBoundary, mockDataAccessBoundary);

        // Setup mock behavior
        String username = "testUser";
        User mockUser = mock(User.class);
        when(mockDataAccessBoundary.get(username)).thenReturn(mockUser);
        when(mockUser.getIsOnline()).thenReturn(false); // Simulate user is offline after logout

        // Execute the method to test
        LogOutInputData inputData = new LogOutInputData(username);
        interactor.execute(inputData);

        // Verify interactions using ArgumentMatchers
        verify(mockOutputBoundary).prepareSuccessView(
                argThat(outputData -> outputData.getUsername().equals(username) &&
                        outputData.getHasLoggedOut() == true)
        );
    }

    @Test
    void testFailedLogOut() {
        // Mock dependencies as before
        LogOutOutputBoundary mockOutputBoundary = mock(LogOutOutputBoundary.class);
        LogOutDataAccessBoundary mockDataAccessBoundary = mock(LogOutDataAccessBoundary.class);

        // Create instance of LogOutInteractor
        LogOutInteractor interactor = new LogOutInteractor(mockOutputBoundary, mockDataAccessBoundary);

        // Setup mock behavior
        String username = "testUser";
        User mockUser = mock(User.class);
        when(mockDataAccessBoundary.get(username)).thenReturn(mockUser);
        when(mockUser.getIsOnline()).thenReturn(false); // Simulate user is offline after logout

        // Execute the method to test
        LogOutInputData inputData = new LogOutInputData(username);
        interactor.execute(inputData);

        // Setup mock behavior for failed logout
        when(mockUser.getIsOnline()).thenReturn(true); // Simulate user is still online

        // Execute the method to test
        interactor.execute(inputData);

        // Verify interactions using ArgumentMatchers
        verify(mockOutputBoundary).prepareFailView(
                argThat(outputData -> outputData.getUsername().equals(username) &&
                        outputData.getHasLoggedOut() == false)
        );
    }
}
