package StateTests;

import chattingSystem.entities.FunJoke.FunJoke;
import chattingSystem.interface_adapter.state.FunJokeState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FunJokeStateTest {

    @Test
    void getJokeInfoDisplayTest() {
        FunJokeState funJokeState = new FunJokeState();
        FunJoke joke = new FunJoke("This is a joke question.", "This is a joke answer.");
        funJokeState.setJokeInfoDisplay(joke);
        assertEquals(joke.toString(), funJokeState.getJokeInfoDisplay());
    }

    @Test
    void setJokeInfoDisplayTest() {
        FunJokeState funJokeState = new FunJokeState();
        FunJoke joke = new FunJoke("This is a joke question.", "This is a joke answer.");
        funJokeState.setJokeInfoDisplay(joke);
        assertEquals(joke.toString(), funJokeState.getJokeInfoDisplay());
    }

    @Test
    void setErrorMessageTest() {
        FunJokeState funJokeState = new FunJokeState();
        funJokeState.setErrorMessage("Error with Joke");
        assertEquals("Error with Joke", funJokeState.getErrorMessage());
    }

    @Test
    void getErrorMessageTest() {
        FunJokeState funJokeState = new FunJokeState();
        funJokeState.setErrorMessage("Error with Joke");
        assertEquals("Error with Joke", funJokeState.getErrorMessage());
    }

    @Test
    void copyConstructorTest() {
        FunJokeState original = new FunJokeState();
        original.setJokeInfoDisplay(new FunJoke("Joke Question.", "Joke Answer."));
        FunJokeState copy = new FunJokeState(original);
        assertEquals(original.getJokeInfoDisplay(), copy.getJokeInfoDisplay());
        assertEquals(original.getErrorMessage(), copy.getErrorMessage());
    }
}
