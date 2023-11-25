package chattingSystem.use_cases.fun_joke;

import chattingSystem.entities.FunJoke.FunJoke;

public class FunJokeOutputData {

    private FunJoke jokeInfo;

    public FunJokeOutputData(FunJoke jokeInfo) {
        this.jokeInfo = jokeInfo;
    }

    public FunJoke getJokeInfo() {
        return jokeInfo;
    }
}
