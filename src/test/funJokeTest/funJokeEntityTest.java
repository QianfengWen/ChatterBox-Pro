package funJokeTest;

import chattingSystem.entities.FunJoke.FunJoke;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class funJokeEntityTest {

    @Test
    public void testFunJokeBuilder() {
        String question = "Why did the chicken cross the road?";
        String answer = "To get to the other side";

        FunJoke funJoke = FunJoke.builder()
                .jokeQuestion(question)
                .jokeAnswer(answer)
                .build();
        FunJoke standardJoke = new FunJoke(question,answer);
        assertEquals(funJoke.toString(), standardJoke.toString());
    }

    @Test
    public void testFunJokeToString() {
        String question = "Why don't scientists trust atoms?";
        String answer = "Because they make up everything";

        FunJoke funJoke = FunJoke.builder()
                .jokeQuestion(question)
                .jokeAnswer(answer)
                .build();

        String expectedToString = "\n" +
                " Joke Question: " + question + "\n" +
                "\n" +
                " Joke Answer: " + answer + "\n";
        assertEquals(expectedToString, funJoke.toString());
    }
}

