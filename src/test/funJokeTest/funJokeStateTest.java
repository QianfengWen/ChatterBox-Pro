package funJokeTest;

import chattingSystem.entities.FunJoke.FunJoke;
import chattingSystem.interface_adapter.state.FunJokeState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class funJokeStateTest {

    @Test
    public void testCopyConstructor() {

        String question = "Why don't scientists trust atoms?";
        String answer = "Because they make up everything";
        FunJokeState originalState = new FunJokeState();
        originalState.setJokeInfoDisplay(new FunJoke(question, answer));
        originalState.setErrorMessage("Original Error");

        FunJokeState copiedState = new FunJokeState(originalState);

        assertEquals(originalState.getJokeInfoDisplay(), copiedState.getJokeInfoDisplay());
        assertEquals("Original Error", copiedState.getErrorMessage());
    }

    @Test
    public void testDefaultConstructor() {
        FunJokeState state = new FunJokeState();

        assertNull(state.getJokeInfoDisplay());
        assertNull(state.getErrorMessage());
    }

    @Test
    public void testSetJokeInfoDisplay() {

        String question = "Why don't scientists trust atoms?";
        String answer = "Because they make up everything";
        FunJokeState state = new FunJokeState();
        state.setJokeInfoDisplay(new FunJoke(question,answer));

        assertEquals(new FunJoke(question,answer).toString(), state.getJokeInfoDisplay().toString());
    }

    @Test
    public void testSetErrorMessage() {
        FunJokeState state = new FunJokeState();
        state.setErrorMessage("New Error");

        assertEquals("New Error", state.getErrorMessage());
    }
}