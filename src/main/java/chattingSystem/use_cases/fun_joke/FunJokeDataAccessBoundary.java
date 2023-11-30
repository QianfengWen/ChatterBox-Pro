package chattingSystem.use_cases.fun_joke;

import chattingSystem.entities.FunJoke.FunJoke;

public interface FunJokeDataAccessBoundary {

    FunJoke getFunJoke();

    boolean JokeIsValid();
}
