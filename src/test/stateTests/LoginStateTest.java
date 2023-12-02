package stateTests;

import chattingSystem.interface_adapter.state.LoginState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginStateTest {

    @Test
    void getUsernameTest() {
        LoginState loginState = new LoginState();
        loginState.setUsername("testUser");
        String result = loginState.getUsername();
        assertEquals("testUser", result);
    }

    @Test
    void setUsernameTest() {
        LoginState loginState = new LoginState();
        loginState.setUsername("testUser");
        assertEquals("testUser", loginState.getUsername());
    }

    @Test
    void getCorrectUsernameErrorTest() {
        LoginState loginState = new LoginState();
        loginState.setUsernameError("Error");
        String result = loginState.getUsernameError();
        assertEquals("Error", result);
    }

    @Test
    void setUsernameErrorTest() {
        LoginState loginState = new LoginState();
        loginState.setUsernameError("Error");
        assertEquals("Error", loginState.getUsernameError());
    }

    @Test
    void getPasswordCorrectPassword() {
        LoginState loginState = new LoginState();
        loginState.setPassword("testPassword");
        String result = loginState.getPassword();
        assertEquals("testPassword", result);
    }

    @Test
    void setPasswordCorrectly() {
        LoginState loginState = new LoginState();
        loginState.setPassword("testPassword");
        assertEquals("testPassword", loginState.getPassword());
    }

    @Test
    void getCorrectSignupSuccess() {
        LoginState loginState = new LoginState();
        loginState.setSignupSuccess(true);
        boolean result = loginState.SignupSuccess();
        assertTrue(result);
    }

    @Test
    void setSignupSuccessCorrectly() {
        LoginState loginState = new LoginState();
        loginState.setSignupSuccess(true);
        assertTrue(loginState.SignupSuccess());
    }

    @Test
    void getCorrectPasswordError() {
        LoginState loginState = new LoginState();
        loginState.setPasswordError("Error");
        String result = loginState.getPasswordError();
        assertEquals("Error", result);
    }

    @Test
    void setPasswordErrorCorrectly() {
        LoginState loginState = new LoginState();
        loginState.setPasswordError("Error");
        assertEquals("Error", loginState.getPasswordError());
    }

    @Test
    void getCorrectLoginSuccess() {
        LoginState loginState = new LoginState();
        loginState.setLoginSuccess(true);
        boolean result = loginState.loginSuccess();
        assertTrue(result);
    }

    @Test
    void setLoginSuccessTest() {
        LoginState loginState = new LoginState();
        loginState.setLoginSuccess(true);
        assertTrue(loginState.loginSuccess());
    }

    @Test
    void copyConstructorTest() {
        LoginState original = new LoginState();
        original.setUsername("testUser");
        original.setUsernameError("Error");
        original.setPassword("testPassword");
        original.setPasswordError("Error");
        original.setSignupSuccess(true);
        original.setLoginSuccess(true);
        LoginState copy = new LoginState(original);
        assertEquals(original.getUsername(), copy.getUsername());
        assertEquals(original.getUsernameError(), copy.getUsernameError());
        assertEquals(original.getPassword(), copy.getPassword());
        assertEquals(original.getPasswordError(), copy.getPasswordError());
        assertEquals(original.SignupSuccess(), copy.SignupSuccess());
        assertEquals(original.loginSuccess(), copy.loginSuccess());
    }
}
