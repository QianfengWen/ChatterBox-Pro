package funJokeTest;

import chattingSystem.entities.FunJoke.FunJoke;
import chattingSystem.interface_adapter.presenter.FunJokePresenter;
import chattingSystem.interface_adapter.state.FunJokeState;
import chattingSystem.interface_adapter.view_models.FunJokeViewModel;
import chattingSystem.use_cases.fun_joke.FunJokeOutputData;
import chattingSystem.use_cases.fun_joke.FunJokeOutputBoundry;
import chattingSystem.use_cases.show_joke.ShowJokeOutputBoundry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class funJokePresenterTest {

    private FunJokeViewModel funJokeViewModel;
    private FunJokePresenter funJokePresenter;

    @BeforeEach
    public void setUp() {
        funJokeViewModel = new FunJokeViewModel();
        funJokePresenter = new FunJokePresenter(funJokeViewModel);
    }

    @Test
    public void testPrepareSuccessView() {

        String question = "Why don't scientists trust atoms?";
        String answer = "Because they make up everything";

        FunJoke funJoke = FunJoke.builder()
                .jokeQuestion(question)
                .jokeAnswer(answer)
                .build();
        FunJokeOutputData jokeInfo = new FunJokeOutputData(funJoke);
        funJokePresenter.prepareSuccessView(jokeInfo);

        FunJokeState currentState = funJokeViewModel.getState();
        assertEquals(funJoke.toString(), currentState.getJokeInfoDisplay());
    }

    @Test
    public void testPrepareFailView() {
        funJokePresenter.prepareFailView("Test Error");

        FunJokeState currentState = funJokeViewModel.getState();
        assertEquals("Test Error", currentState.getErrorMessage());
    }

    @Test
    public void testPrepareSuccessViewWithoutJokeInfo() {
        funJokePresenter.prepareSuccessView();

    }

    @Test
    public void testPrepareFailViewWithoutError() {
        funJokePresenter.prepareFailView();

    }
}
