import chattingSystem.entities.User.CommonUser;
import chattingSystem.entities.User.CommonUserFactory;
import chattingSystem.entities.User.User;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class UserEntityTest {
    String username = "username";
    String password = "password";
    String userid = "1";
    private CommonUserFactory commonUserFactory = new CommonUserFactory();
    LocalDateTime localDateTime = LocalDateTime.now();

    @Test
    public void createUserSuccessfulFactory() {
        User user = commonUserFactory.create(username, userid, password, localDateTime);
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(userid, user.getUserid());
        assertEquals(localDateTime, user.getCreationTime());
        assertFalse(user.getIsOnline());
    }
    @Test
    public void createUserSuccessfulConstructor() {
        User user = new CommonUser(username, userid, password, localDateTime);
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(userid, user.getUserid());
        assertEquals(localDateTime, user.getCreationTime());
        assertFalse(user.getIsOnline());
    }
    @Test
    public void testSetOnline() {
        User user = new CommonUser(username, userid, password, localDateTime);
        user.setOnline(true);
        assertTrue(user.getIsOnline());
    }
}

