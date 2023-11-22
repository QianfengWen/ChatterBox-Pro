package chattingSystem.frameworks_drivers.api;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class MongoDownloadChatRoom {
    public JSONObject downloadChatRoom(String chatRoomId) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n      \"dataSource\": \"OnlineChatting\",\n      \"database\": \"ChatSystem\",\n      \"collection\": \"chatrooms\",\n      \"filter\": { \"ChatRoomId\": \""+chatRoomId+"\"}\n  }");
        Request request = new Request.Builder()
                .url("https://us-east-2.aws.data.mongodb-api.com/app/data-ybzsr/endpoint/data/v1/action/findOne")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Request-Headers", "*")
                .addHeader("api-key", "AbgMkEbqaQgltaqv4Z7ui1Jd6XVJ2rQzj5Hw8P3IMDeVdND2CGCICNTid0JvpQwm")
                .addHeader("Accept", "application/j")
                .build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONObject chatRooomJson = new JSONObject(responseString).getJSONObject("document");
        return chatRooomJson;
    }
}
