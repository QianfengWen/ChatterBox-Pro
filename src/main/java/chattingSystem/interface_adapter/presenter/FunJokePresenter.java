package chattingSystem.interface_adapter.presenter;

import chattingSystem.frameworks_drivers.api.FunJokeDataAccessObject;
import chattingSystem.interface_adapter.state.FunJokeState;
import chattingSystem.interface_adapter.view_models.FunJokeViewModel;
import chattingSystem.use_cases.fun_joke.FunJokeOutputBoundry;
import chattingSystem.use_cases.fun_joke.FunJokeOutputData;
import chattingSystem.use_cases.show_joke.ShowJokeOutputBoundry;

import static chattingSystem.App.ShowJokeFrameFactory.createShowJokeFrame;

public class FunJokePresenter implements FunJokeOutputBoundry, ShowJokeOutputBoundry {


    private final FunJokeViewModel funJokeViewModel;

    public FunJokePresenter(FunJokeViewModel funJokeViewModel) {

        this.funJokeViewModel = funJokeViewModel;
    }

    @Override
    public void prepareSuccessView(FunJokeOutputData jokeInfo) {

        FunJokeState currentState = funJokeViewModel.getState();
        currentState.setJokeInfoDisplay(jokeInfo.getJokeInfo());
        funJokeViewModel.setState(currentState);
        funJokeViewModel.firePropertyChanged();
    }



    @Override
    public void prepareFailView(String error) {
        FunJokeState currentState = funJokeViewModel.getState();
        currentState.setErrorMessage(error);
        funJokeViewModel.firePropertyChanged();
    }


    @Override
    public void prepareSuccessView() {
        FunJokeDataAccessObject funJokeDataAccessBoundary = new FunJokeDataAccessObject();
        createShowJokeFrame(funJokeViewModel, funJokeDataAccessBoundary);


    }

    @Override
    public void prepareFailView() {

    }
}
