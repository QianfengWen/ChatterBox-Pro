package chattingSystem.use_cases.fun_joke;

import chattingSystem.entities.FunJoke.FunJoke;

public class FunJokeInteractor implements FunJokeInputBountry{

    final FunJokeDataAccessBoundary funJokeDataAccessObject;

    final FunJokeOutputBoundry funJokePresenter;


    public FunJokeInteractor(FunJokeDataAccessBoundary funJokeDataAccessObject, FunJokeOutputBoundry funJokeOutputBoundry) {

        this.funJokeDataAccessObject = funJokeDataAccessObject;

        this.funJokePresenter = funJokeOutputBoundry;
    }

    @Override
    public void execute(FunJokeInputData funJokeInputData) {
        if (funJokeDataAccessObject.JokeIsValid()) {
            FunJoke jokeInfo = funJokeDataAccessObject.getFunJoke();
            FunJokeOutputData funJokeOutputData = new FunJokeOutputData(jokeInfo);

            funJokePresenter.prepareSuccessView(funJokeOutputData);
        }
        else {
            funJokePresenter.prepareFailView("Unable to get a funny joke.");
        }
    }
}
