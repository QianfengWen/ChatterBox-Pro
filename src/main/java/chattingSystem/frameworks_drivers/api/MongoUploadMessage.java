package chattingSystem.frameworks_drivers.api;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

public class MongoUploadMessage {
    public void uploadMessage(String messageid, String username, String userid, String message, String creationTime){
        HttpResponse<String> response = Unirest.post("https://us-east-2.aws.data.mongodb-api.com/app/data-ybzsr/endpoint/data/v1/action/insertOne")
                .header("Content-Type", "application/json")
                .header("Access-Control-Request-Headers", "*")
                .header("api-key", "AbgMkEbqaQgltaqv4Z7ui1Jd6XVJ2rQzj5Hw8P3IMDeVdND2CGCICNTid0JvpQwm")
                .body("{\n      \"dataSource\": \"OnlineChatting\",\n      \"database\": \"ChatSystem\",\n      \"collection\": \"messages\",\n      \"document\": {\n        \"username\": \""+username+"\",\n        \"userid\": \""+userid+"\",\n        \"message\": \""+message+"\",\n        \"creation_time\":\""+creationTime+"\",\n        \"messageid\": \""+messageid+"\"\n      }\n  }")
                .asString();


    }
}
