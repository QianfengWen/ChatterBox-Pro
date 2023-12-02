package loginTest;

import chattingSystem.frameworks_drivers.ui.views.ViewManager;
import chattingSystem.interface_adapter.presenter.LoginPresenter;
import chattingSystem.interface_adapter.state.LoginState;
import chattingSystem.interface_adapter.view_models.LoginViewModel;
import chattingSystem.interface_adapter.view_models.ViewManagerModel;
import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import chattingSystem.use_cases.login.LoginOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class LoginPresenterTest {

    @Mock
    private LoginViewModel mockLoginViewModel;
    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private LoginState mockLoginState;
    @Mock
    private ViewManager mockViewManager;
    @Mock
    private LogOutDataAccessBoundary mockLogOutDataAccessBoundary;

    private LoginPresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockLoginViewModel.getState()).thenReturn(mockLoginState);
        presenter = new LoginPresenter(mockViewManagerModel, mockLoginViewModel, mockViewManager);
    }

    @Test
    void testPrepareSuccessView() {
        // Assuming login was successful
        LoginOutputData response = new LoginOutputData("testUser", false);
        when(mockViewManagerModel.getActiveView()).thenReturn("log in");

        // Execute the method to test
        presenter.prepareSuccessView(response, mockLogOutDataAccessBoundary);

        // Verify that LoginState is updated correctly
        verify(mockLoginViewModel).firePropertyChanged();
        // Further verifications can be done based on the actual implementation
    }

    @Test
    void testPrepareFailView() {
        String error = "Login failed";

        // Execute the method to test
        presenter.prepareFailView(error);

        // Verify that LoginState is updated with the error
        verify(mockLoginViewModel).firePropertyChanged();
        // Additional assertions can be added to check if the usernameError in LoginState is set correctly
    }
}
