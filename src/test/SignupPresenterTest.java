import chattingSystem.interface_adapter.presenter.SignupPresenter;
import chattingSystem.interface_adapter.state.LoginState;
import chattingSystem.interface_adapter.state.SignupState;
import chattingSystem.interface_adapter.view_models.LoginViewModel;
import chattingSystem.interface_adapter.view_models.SignupViewModel;
import chattingSystem.interface_adapter.view_models.ViewManagerModel;
import chattingSystem.use_cases.signup.SignupOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class SignupPresenterTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private SignupViewModel mockSignupViewModel;
    @Mock
    private LoginViewModel mockLoginViewModel;
    @Mock
    private SignupState mockSignupState;
    @Mock
    private LoginState mockLoginState;

    private SignupPresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new SignupPresenter(mockViewManagerModel, mockSignupViewModel, mockLoginViewModel);
        when(mockSignupViewModel.getState()).thenReturn(mockSignupState);
        when(mockLoginViewModel.getState()).thenReturn(mockLoginState);
    }

    @Test
    void testPrepareSuccessView() {
        SignupOutputData responseData = new SignupOutputData("testUser", LocalDateTime.now().toString(), false);
        presenter.prepareSuccessView(responseData);

        verify(mockSignupViewModel).firePropertyChanged();
        verify(mockLoginState).setUsername("testUser");
        verify(mockLoginState).setSignupSuccess(true);
        verify(mockLoginViewModel).setState(mockLoginState);
        verify(mockLoginViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView(mockLoginViewModel.getViewName());
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    void testPrepareNameFailView() {
        String error = "Username error";
        presenter.prepareNameFailView(error);

        verify(mockSignupState).setUsernameError(error);
        verify(mockSignupViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareInvalidPWFailView() {
        String error = "Invalid password error";
        presenter.prepareInvalidPWFailView(error);

        verify(mockSignupState).setPasswordError(error);
        verify(mockSignupViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareRepeatPWFailView() {
        String error = "Repeat password error";
        presenter.prepareRepeatPWFailView(error);

        verify(mockSignupState).setRepeatPasswordError(error);
        verify(mockSignupViewModel).firePropertyChanged();
    }
}
