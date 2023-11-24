package chattingSystem.frameworks_drivers.api;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MongoDownloadMessage {
    public List<JSONObject> downloadMessage() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/jason");
        RequestBody body = RequestBody.create(mediaType, "{\n      \"dataSource\": \"OnlineChatting\",\n      \"database\": \"ChatSystem\",\n      \"collection\": \"messages\",\n      \"filter\": {}\n  }");
        Request request = new Request.Builder()
                .url("https://us-east-2.aws.data.mongodb-api.com/app/data-ybzsr/endpoint/data/v1/action/find")
                .method("POST", body)
                .addHeader("Content-Type", "application/jason")
                .addHeader("Access-Control-Request-Headers", "*")
                .addHeader("api-key", "AbgMkEbqaQgltaqv4Z7ui1Jd6XVJ2rQzj5Hw8P3IMDeVdND2CGCICNTid0JvpQwm")
                .addHeader("Accept", "application/jason")
                .build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONObject responseJson = new JSONObject(responseString);
        JSONArray responseJsonArray = responseJson.getJSONArray("documents");
        List<JSONObject> Messages = new ArrayList<>();
        if (responseJsonArray.isEmpty()) {
            return Messages;
        }
        for (int i = 0; i < responseJsonArray.length(); i++) {
            JSONObject message = responseJsonArray.getJSONObject(i);
            Messages.add(message);
        }
        return Messages;

    }
}
