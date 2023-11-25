package chattingSystem.interface_adapter.controllers;

import chattingSystem.use_cases.show_joke.ShowJokeInputBoundry;

import java.io.IOException;

public class ShowJokeController {

    final ShowJokeInputBoundry showJokeInteractor;

    public ShowJokeController(ShowJokeInputBoundry showJokeInteractor) {
        this.showJokeInteractor = showJokeInteractor;
    }

    public void execute() throws IOException {
        showJokeInteractor.execute();
    }
}
