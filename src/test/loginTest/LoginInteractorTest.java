package loginTest;

import chattingSystem.entities.User.CommonUser;
import chattingSystem.entities.User.User;
import chattingSystem.interface_adapter.state.LoginState;
import chattingSystem.use_cases.login.LoginInputData;
import chattingSystem.use_cases.login.LoginInteractor;
import chattingSystem.use_cases.login.LoginOutputBoundary;
import chattingSystem.use_cases.login.LoginOutputData;
import chattingSystem.use_cases.login.LoginUserDataAccessInterface;
import chattingSystem.use_cases.log_out.LogOutDataAccessBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDateTime;

class LoginInteractorTest {

    private class MockUserDataAccess implements LoginUserDataAccessInterface {
        private final User testUser;

        public MockUserDataAccess(User testUser) {
            this.testUser = testUser;
        }

        @Override
        public boolean existsByName(String identifier) {
            return testUser.getUsername().equals(identifier);
        }

        @Override
        public void save(User user) throws IOException {
            // Do nothing for the test
        }

        @Override
        public User get(String username) {
            return testUser;
        }
    }

    private class MockLoginPresenter implements LoginOutputBoundary {
        private LoginOutputData successOutputData;
        private String failError;

        @Override
        public void prepareSuccessView(LoginOutputData user, LogOutDataAccessBoundary logOutDataAccessBoundary) {
            this.successOutputData = user;
        }

        @Override
        public void prepareFailView(String error) {
            this.failError = error;
        }
    }

    private class MockLogOutDataAccess implements LogOutDataAccessBoundary {
        @Override
        public void logOut(String username) {
            // Do nothing for the test
        }

        @Override
        public User get(String username) {
            return null;
        }
    }

    private LoginInteractor loginInteractor;
    private MockUserDataAccess mockUserDataAccess;
    private MockLoginPresenter mockLoginPresenter;
    private MockLogOutDataAccess mockLogOutDataAccess;

    @BeforeEach
    void setUp() {
        User testUser = new CommonUser("testUser", "123", "password", LocalDateTime.now());
        mockUserDataAccess = new MockUserDataAccess(testUser);
        mockLoginPresenter = new MockLoginPresenter();
        mockLogOutDataAccess = new MockLogOutDataAccess();
        loginInteractor = new LoginInteractor(mockUserDataAccess, mockLoginPresenter, mockLogOutDataAccess);
    }

    @Test
    void testExecute_SuccessfulLogin() {
        LoginInputData inputData = new LoginInputData("testUser", "password");
        loginInteractor.execute(inputData);

        assertNotNull(mockLoginPresenter.successOutputData);
        assertEquals("testUser", mockLoginPresenter.successOutputData.getUsername());
        assertFalse(mockLoginPresenter.successOutputData.isUseCaseFailed());
    }

    @Test
    void testExecute_AccountDoesNotExist() {
        LoginInputData inputData = new LoginInputData("nonexistentUser", "password");
        loginInteractor.execute(inputData);

        assertNull(mockLoginPresenter.successOutputData);
        assertEquals("nonexistentUser: Account does not exist.", mockLoginPresenter.failError);
    }

    @Test
    void testExecute_AccountAlreadyOnline() {
        User onlineUser = new CommonUser("onlineUser", "456", "password", LocalDateTime.now());
        onlineUser.setOnline(true);
        mockUserDataAccess = new MockUserDataAccess(onlineUser);
        loginInteractor = new LoginInteractor(mockUserDataAccess, mockLoginPresenter, mockLogOutDataAccess);

        LoginInputData inputData = new LoginInputData("onlineUser", "password");
        loginInteractor.execute(inputData);

        assertNull(mockLoginPresenter.successOutputData);
        assertEquals("onlineUser: Account is already online.", mockLoginPresenter.failError);
    }

    @Test
    void testExecute_IncorrectPassword() {
        LoginInputData inputData = new LoginInputData("testUser", "wrongPassword");
        loginInteractor.execute(inputData);

        assertNull(mockLoginPresenter.successOutputData);
        assertEquals("Incorrect password for testUser.", mockLoginPresenter.failError);
    }
}