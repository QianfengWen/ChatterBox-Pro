package chattingSystem.interface_adapter.state;

import chattingSystem.entities.FunJoke.FunJoke;

public class FunJokeState {

    private String jokeInfoDisplay;
    private String errorMessage = null;

    public FunJokeState(FunJokeState copy) {
        jokeInfoDisplay = copy.jokeInfoDisplay;
        errorMessage = copy.errorMessage;
    }

    public FunJokeState() {
    }

    public String getJokeInfoDisplay() {
        return jokeInfoDisplay;
    }

    public void setJokeInfoDisplay(FunJoke jokeInfoDisplay) {
        this.jokeInfoDisplay = jokeInfoDisplay.toString();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {return errorMessage;}
}
