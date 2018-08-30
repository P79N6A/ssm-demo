package com.exams;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author fangcong on 2018/8/28.
 */
public class MockRequest {

    public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=e8ea5490233ca3de3d50bec02ef4cfa607c39708338b4ba30452664f9f4cd49e";

    public static void main(String args[]) throws Exception{

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = "{\n"
            + "     \"msgtype\": \"text\",\n"
            + "     \"text\": {\n"
            + "         \"content\": \"@13972879594 瞎搞，翻车了吧\"\n"
            + "     },\n"
            + "     \"at\": {\n"
            + "         \"atMobiles\": [\n"
            + "             \"13972879594\"\n"
            + "         ], \n"
            + "         \"isAtAll\": false\n"
            + "     }\n"
            + " }";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String result= EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }
}
