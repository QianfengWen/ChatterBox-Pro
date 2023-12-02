package funJokeTest;

import chattingSystem.entities.FunJoke.FunJoke;
import chattingSystem.use_cases.fun_joke.*;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class funJokeUseCaseTest {

    @Test
    public void testGenerateJokeSuccessfulInteractor() {
//        FunJokeDataAccessBoundary funJokeDataAccessObject = new FunJokeDataAccessObject();
        FunJokeDataAccessBoundary funJokeDataAccessBoundary = new FunJokeDataAccessBoundary() {
            @Override
            public FunJoke getFunJoke() {
                return new FunJoke("hello?", "ok");
            }

            @Override
            public boolean JokeIsValid() {
                return true;
            }
        };
        FunJokeOutputBoundry funJokePresenter = new FunJokeOutputBoundry() {
            @Override
            public void prepareSuccessView(FunJokeOutputData joke) {
                assertTrue(funJokeDataAccessBoundary.JokeIsValid());
                assertTrue(joke.getJokeInfo() instanceof FunJoke);
            }
            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unable to get a funny joke.");
            }
        };
        FunJokeInputData funJokeInputData = null;
        FunJokeInteractor funJokeInteractor =  new FunJokeInteractor(funJokeDataAccessBoundary,
                funJokePresenter );
        funJokeInteractor.execute(funJokeInputData);
    }

    @Test
    public void testGenerateJokeFailViewInteractor() {
        FunJokeDataAccessBoundary funJokeDataAccessBoundary1 = new FunJokeDataAccessBoundary() {
            @Override
            public FunJoke getFunJoke() {
                throw new RuntimeException("Unable to get a funny joke.");
            }

            @Override
            public boolean JokeIsValid() {
                return false;
            }
        };
        final boolean[] prepareFailViewcalled = {false};
        FunJokeOutputBoundry funJokePresenter1 = new FunJokeOutputBoundry() {
            @Override
            public void prepareSuccessView(FunJokeOutputData funJokeOutputData) {

            }

            @Override
            public void prepareFailView(String errorMessage) {
                prepareFailViewcalled[0] = true;
            }
        };
        FunJokeInteractor funJokeInteractor1 = new FunJokeInteractor(funJokeDataAccessBoundary1, funJokePresenter1);
        FunJokeInputData funJokeInputData1 = null;
        funJokeInteractor1.execute(funJokeInputData1);
        assertTrue(prepareFailViewcalled[0]);
    }

    @org.junit.jupiter.api.Test
    void testJokeOutputDataSetJokeInfo() {

        // Arrange
        String question = "Why don't scientists trust atoms?";
        String answer = "Because they make up everything";

        FunJoke funJoke = FunJoke.builder()
                .jokeQuestion(question)
                .jokeAnswer(answer)
                .build();

        // Act
        FunJokeOutputData outputData = new FunJokeOutputData(funJoke);
        // Assert
        // Directly inspect the state of the outputData object
        assertNotNull(outputData);
        assertEquals(funJoke, outputData.getJokeInfo());
    }

    @org.junit.jupiter.api.Test
    void testJokeOutputDataGetJokeInfo() {

        // Arrange
        String question = "Why don't scientists trust atoms?";
        String answer = "Because they make up everything";

        FunJoke funJoke = FunJoke.builder()
                .jokeQuestion(question)
                .jokeAnswer(answer)
                .build();

        // Act
        FunJokeOutputData outputData = new FunJokeOutputData(funJoke);

        // Act
        FunJoke result = outputData.getJokeInfo();

        // Assert
        assertEquals(funJoke, result);
    }
}