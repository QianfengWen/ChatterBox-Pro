package signupTest;

import chattingSystem.entities.User.User;
import chattingSystem.entities.User.UserFactory;
import chattingSystem.use_cases.signup.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignupInteractorTest {

    @Mock
    private SignupUserDataAccessInterface mockUserDataAccess;

    @Mock
    private SignupOutputBoundary mockSignupPresenter;

    @InjectMocks
    private SignupInteractor signupInteractor;


    private class MockUserFactory implements UserFactory {
        @Override
        public User create(String username, String userid, String password, LocalDateTime creationTime) {

            return new MockUser(username, userid, password, creationTime);
        }
    }


    private class MockUser implements User {
        private final String username;
        private final String userid;
        private final String password;
        private final LocalDateTime creationTime;

        public MockUser(String username, String userid, String password, LocalDateTime creationTime) {
            this.username = username;
            this.userid = userid;
            this.password = password;
            this.creationTime = creationTime;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public String getUserid() {
            return userid;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public LocalDateTime getCreationTime() {
            return creationTime;
        }

        @Override
        public boolean getIsOnline() {
            return false;
        }

        @Override
        public void setOnline(boolean isOnline) {

        }

    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        signupInteractor = new SignupInteractor(mockUserDataAccess, mockSignupPresenter, new MockUserFactory());
    }

    @Test
    void testExecute_SuccessfulSignup() throws IOException {
        String newUsername = "newUser";
        String password = "NewPassword123";

        when(mockUserDataAccess.existsByName(newUsername)).thenReturn(false);
        when(mockUserDataAccess.generateUserid()).thenReturn("2");
        doAnswer(invocation -> {
            User savedUser = invocation.getArgument(0, User.class);
            assertEquals(newUsername, savedUser.getUsername());
            assertEquals("2", savedUser.getUserid());
            assertEquals(password, savedUser.getPassword());
            assertNotNull(savedUser.getCreationTime());
            return null;
        }).when(mockUserDataAccess).save(any(User.class));

        signupInteractor.execute(new SignupInputData(newUsername, password, password));

        verify(mockSignupPresenter, times(1)).prepareSuccessView(any(SignupOutputData.class));
        verify(mockSignupPresenter, never()).prepareNameFailView(anyString());
        verify(mockSignupPresenter, never()).prepareRepeatPWFailView(anyString());
        verify(mockSignupPresenter, never()).prepareInvalidPWFailView(anyString());
    }

    @Test
    void testExecute_UserAlreadyExists() throws IOException {
        String existingUsername = "existingUser";
        String password = "Password123";

        when(mockUserDataAccess.existsByName(existingUsername)).thenReturn(true);

        signupInteractor.execute(new SignupInputData(existingUsername, password, password));

        verify(mockSignupPresenter, never()).prepareSuccessView(any(SignupOutputData.class));
        verify(mockSignupPresenter, times(1)).prepareNameFailView(anyString());
        verify(mockSignupPresenter, never()).prepareRepeatPWFailView(anyString());
        verify(mockSignupPresenter, never()).prepareInvalidPWFailView(anyString());
    }

    @Test
    void testExecute_PasswordsDoNotMatch() throws IOException {
        String newUsername = "newUser";
        String password1 = "Password123";
        String password2 = "DifferentPassword";

        when(mockUserDataAccess.existsByName(newUsername)).thenReturn(false);

        signupInteractor.execute(new SignupInputData(newUsername, password1, password2));

        verify(mockSignupPresenter, never()).prepareSuccessView(any(SignupOutputData.class));
        verify(mockSignupPresenter, never()).prepareNameFailView(anyString());
        verify(mockSignupPresenter, times(1)).prepareRepeatPWFailView(anyString());
        verify(mockSignupPresenter, never()).prepareInvalidPWFailView(anyString());
    }

    @Test
    void testExecute_InvalidPassword() throws IOException {
        String newUsername = "newUser";
        String invalidPassword = "password";

        when(mockUserDataAccess.existsByName(newUsername)).thenReturn(false);

        signupInteractor.execute(new SignupInputData(newUsername, invalidPassword, invalidPassword));

        verify(mockSignupPresenter, never()).prepareSuccessView(any(SignupOutputData.class));
        verify(mockSignupPresenter, never()).prepareNameFailView(anyString());
        verify(mockSignupPresenter, never()).prepareRepeatPWFailView(anyString());
        verify(mockSignupPresenter, times(1)).prepareInvalidPWFailView(anyString());
    }
    @Test
    void testExecute_SaveUser() throws IOException {
        String username = "newUser";
        String password = "Password123";

        when(mockUserDataAccess.existsByName(username)).thenReturn(false);

        signupInteractor.execute(new SignupInputData(username, password, password));

        verify(mockUserDataAccess, times(1)).save(any(User.class));
    }
    @Test
    void testConstructorAndGetters() {
        String username = "testUser";
        String creationTime = "2023-12-01T23:04:33.549708";
        boolean useCaseFailed = false;

        SignupOutputData signupOutputData = new SignupOutputData(username, creationTime, useCaseFailed);

        assertEquals(username, signupOutputData.getUsername());
        assertEquals(creationTime, signupOutputData.getCreationTime());
        assertEquals(useCaseFailed, signupOutputData.getUseCaseFailed());
    }

    @Test
    void testSetCreationTime() {
        String username = "testUser";
        String creationTime = "2023-12-01T23:04:33.549708";
        boolean useCaseFailed = false;

        SignupOutputData signupOutputData = new SignupOutputData(username, creationTime, useCaseFailed);

        String newCreationTime = "2023-12-01T23:10:23.692097";
        signupOutputData.setCreationTime(newCreationTime);

        assertEquals(newCreationTime, signupOutputData.getCreationTime());
    }

    @Test
    void testSetUseCaseFailed() {
        String username = "testUser";
        String creationTime = "2023-12-01T23:04:33.549708";
        boolean useCaseFailed = false;

        SignupOutputData signupOutputData = new SignupOutputData(username, creationTime, useCaseFailed);

        signupOutputData.setUseCaseFailed(true);

        assertTrue(signupOutputData.getUseCaseFailed());
    }

}

