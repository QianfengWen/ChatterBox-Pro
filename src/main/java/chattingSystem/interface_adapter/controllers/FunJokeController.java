package chattingSystem.interface_adapter.controllers;

import chattingSystem.use_cases.fun_joke.FunJokeInputBountry;
import chattingSystem.use_cases.fun_joke.FunJokeInputData;

public class FunJokeController {

    final FunJokeInputBountry funJokeInteractor;


    public FunJokeController(FunJokeInputBountry funJokeInteractor) {
        this.funJokeInteractor = funJokeInteractor;
    }

    public void execute(){

        FunJokeInputData funJokeInputData = new FunJokeInputData();

        funJokeInteractor.execute(funJokeInputData);
    }
}
