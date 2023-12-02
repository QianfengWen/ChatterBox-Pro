package loginTest;

import chattingSystem.entities.FunJoke.FunJoke;
import chattingSystem.entities.Password.Password;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class passWordEntityTest {

    @Test
    public void testPassWordConstructor() {
        String typein = "Pl123459e!!";
        Password password = new Password(typein);
        assertEquals(typein, password.getPassword());
    }

    @Test
    public void testPassWordIsValid() {
        Password password1 = new Password("pl12345");
        assertFalse(password1.isValid());
    }
}
