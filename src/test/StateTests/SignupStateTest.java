package StateTests;

import chattingSystem.interface_adapter.state.SignupState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SignupStateTest {

    @Test
    void getUsernameTest() {
        SignupState signupState = new SignupState();
        signupState.setUsername("user");
        String result = signupState.getUsername();
        assertEquals("user", result);
    }

    @Test
    void setUsernameTest() {
        SignupState signupState = new SignupState();
        signupState.setUsername("user");
        assertEquals("user", signupState.getUsername());
    }

    @Test
    void getUsernameErrorTest() {
        SignupState signupState = new SignupState();
        signupState.setUsernameError("usernameError");
        String result = signupState.getUsernameError();
        assertEquals("usernameError", result);
    }

    @Test
    void setUsernameErrorTest() {
        SignupState signupState = new SignupState();
        signupState.setUsernameError("usernameError");
        assertEquals("usernameError", signupState.getUsernameError());
    }

    @Test
    void getPasswordTest() {
        SignupState signupState = new SignupState();
        signupState.setPassword("password");
        String result = signupState.getPassword();
        assertEquals("password", result);
    }

    @Test
    void setPasswordTest() {
        SignupState signupState = new SignupState();
        signupState.setPassword("password");
        assertEquals("password", signupState.getPassword());
    }

    @Test
    void getPasswordErrorTest() {
        SignupState signupState = new SignupState();
        signupState.setPasswordError("passwordError");
        String result = signupState.getPasswordError();
        assertEquals("passwordError", result);
    }

    @Test
    void setPasswordErrorTest() {
        SignupState signupState = new SignupState();
        signupState.setPasswordError("passwordError");
        assertEquals("passwordError", signupState.getPasswordError());
    }

    @Test
    void getRepeatPasswordTest() {
        SignupState signupState = new SignupState();
        signupState.setRepeatPassword("repeatPassword");
        String result = signupState.getRepeatPassword();
        assertEquals("repeatPassword", result);
    }

    @Test
    void setRepeatPasswordTest() {
        SignupState signupState = new SignupState();
        signupState.setRepeatPassword("repeatPassword");
        assertEquals("repeatPassword", signupState.getRepeatPassword());
    }

    @Test
    void getRepeatPasswordErrorTest() {
        SignupState signupState = new SignupState();
        signupState.setRepeatPasswordError("repeatPasswordError");
        String result = signupState.getRepeatPasswordError();
        assertEquals("repeatPasswordError", result);
    }

    @Test
    void setRepeatPasswordErrorTest() {
        SignupState signupState = new SignupState();
        signupState.setRepeatPasswordError("repeatPasswordError");
        assertEquals("repeatPasswordError", signupState.getRepeatPasswordError());
    }

    @Test
    void copyConstructorTest() {
        SignupState original = new SignupState();
        original.setUsername("user");
        original.setUsernameError("usernameError");
        original.setPassword("password");
        original.setPasswordError("passwordError");
        original.setRepeatPassword("repeatPassword");
        original.setRepeatPasswordError("repeatPasswordError");
        SignupState copy = new SignupState(original);
        assertEquals(original.getUsername(), copy.getUsername());
        assertEquals(original.getUsernameError(), copy.getUsernameError());
        assertEquals(original.getPassword(), copy.getPassword());
        assertEquals(original.getPasswordError(), copy.getPasswordError());
        assertEquals(original.getRepeatPassword(), copy.getRepeatPassword());
        assertEquals(original.getRepeatPasswordError(), copy.getRepeatPasswordError());
    }

    @Test
    void toStringTest() {
        SignupState signupState = new SignupState();
        signupState.setUsername("user");
        signupState.setPassword("password");
        signupState.setRepeatPassword("repeatPassword");

        String result = signupState.toString();

        assertTrue(result.contains("user"));
        assertTrue(result.contains("password"));
        assertTrue(result.contains("repeatPassword"));
    }
}
