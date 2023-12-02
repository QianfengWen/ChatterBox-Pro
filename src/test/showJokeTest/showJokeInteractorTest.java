package showJokeTest;

import chattingSystem.use_cases.show_joke.ShowJokeInteractor;
import chattingSystem.use_cases.show_joke.ShowJokeOutputBoundry;
import org.junit.Test;

public class showJokeInteractorTest {

    @Test
    public void testJokeInteractorTest() {
        final boolean[] runSuccess = {false};
        ShowJokeOutputBoundry showJokeOutputBoundry = new ShowJokeOutputBoundry() {
            @Override
            public void prepareSuccessView() {
                runSuccess[0] = true;
            }

            @Override
            public void prepareFailView() {

            }
        };
        ShowJokeInteractor showJokeInteractor = new ShowJokeInteractor(showJokeOutputBoundry);
        showJokeInteractor.execute();
    }
}
