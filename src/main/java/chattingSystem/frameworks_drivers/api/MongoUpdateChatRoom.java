package chattingSystem.frameworks_drivers.api;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import okhttp3.*;

import java.io.IOException;

public class MongoUpdateChatRoom {
    public void updateChatRoomsMembers(String chatRoomId, String MembersId) throws IOException {
        HttpResponse<String> response = Unirest.post("https://us-east-2.aws.data.mongodb-api.com/app/data-ybzsr/endpoint/data/v1/action/updateOne")
                .header("Content-Type", "application/json")
                .header("Access-Control-Request-Headers", "*")
                .header("api-key", "AbgMkEbqaQgltaqv4Z7ui1Jd6XVJ2rQzj5Hw8P3IMDeVdND2CGCICNTid0JvpQwm")
                .body("{\n      \"dataSource\": \"OnlineChatting\",\n      \"database\": \"ChatSystem\",\n      \"collection\": \"chatrooms\",\n      \"filter\": { \"ChatRoomId\": \""+chatRoomId+"\" },\n      \"update\": { \"$set\": { \"MembersId\": \""+MembersId+"\" } }\n  }")
                .asString();


    }

}
