package chattingSystem.frameworks_drivers.api;

import chattingSystem.entities.FunJoke.FunJoke;
import chattingSystem.use_cases.fun_joke.FunJokeDataAccessBoundary;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class FunJokeDataAccessObject implements FunJokeDataAccessBoundary {
    private static final String Joke_API_URL = "https://v2.jokeapi.dev/joke/Any?safe-mode/blacklistFlags=racist,sexist&type=twopart";

    public FunJokeDataAccessObject() {
    }

    @Override
    public FunJoke getFunJoke() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(Joke_API_URL)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            if (response.code() == 200) {
                return FunJoke.builder()
                        .jokeQuestion(responseBody.getString("setup"))
                        .jokeAnswer(responseBody.getString("delivery"))
                        .build();
            } else {
                throw new RuntimeException("Joke cannot be displayed!!");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean JokeIsValid() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(Joke_API_URL)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            if (response.code() == 200) {
                return true;
            }
            else {throw new RuntimeException("Joke cannot be displayed!!");}
        }
        catch (IOException | JSONException e){
            return false;
        }
    }
}