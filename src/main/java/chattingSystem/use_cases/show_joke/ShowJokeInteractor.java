package chattingSystem.use_cases.show_joke;

public class ShowJokeInteractor implements ShowJokeInputBoundry {

    private ShowJokeOutputBoundry showJokeOutputBoundry;

    public ShowJokeInteractor(ShowJokeOutputBoundry showJokeOutputBoundry) {
        this.showJokeOutputBoundry = showJokeOutputBoundry;
    }

    @Override
    public void execute()  {
        showJokeOutputBoundry.prepareSuccessView();
    }
}
