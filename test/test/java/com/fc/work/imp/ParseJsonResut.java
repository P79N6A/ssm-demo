package com.fc.work.imp;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.fc.work.imp.ResultBean.DataEntity.ResultEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author fangcong on 2017/6/13.
 */
public class ParseJsonResut {

    private static final String resultStr = "{\"info\":{\"ok\":true,\"code\":\"S001\",\"message\":\"调用成功\"},"
        + "\"data\":{\"result\":[{\"id\":0,\"userNick\":\"晴****彦\",\"target\":\"view_pop\",\"mount\":0,"
        + "\"createTime\":\"2017-06-13 10:09:25.0\"},{\"id\":0,\"userNick\":\"晴****彦\",\"target\":\"leftFollow\","
        + "\"mount\":0,\"createTime\":\"2017-06-13 11:01:48.0\"},{\"id\":0,\"userNick\":\"晴****彦\","
        + "\"target\":\"rightFollow\",\"mount\":0,\"createTime\":\"2017-06-13 11:02:13.0\"},{\"id\":0,"
        + "\"userNick\":\"晴****彦\",\"target\":\"view_pop1\",\"mount\":0,\"createTime\":\"2017-06-13 11:11:32.0\"},"
        + "{\"id\":0,\"userNick\":\"晴****彦\",\"target\":\"qafollow\",\"mount\":0,\"createTime\":\"2017-06-13 14:04:35"
        + ".0\"},{\"id\":0,\"userNick\":\"晴****彦\",\"target\":\"qafollow3\",\"mount\":0,\"createTime\":\"2017-06-13 "
        + "14:05:39.0\"},{\"id\":0,\"userNick\":\"晴****彦\",\"target\":\"qafollow4\",\"mount\":0,"
        + "\"createTime\":\"2017-06-13 14:05:42.0\"},{\"id\":0,\"userNick\":\"晴****彦\",\"target\":\"view_pop2\","
        + "\"mount\":0,\"createTime\":\"2017-06-13 14:08:59.0\"},{\"id\":0,\"userNick\":\"晴****彦\","
        + "\"target\":\"view_pops\",\"mount\":0,\"createTime\":\"2017-06-13 14:12:42.0\"},{\"id\":0,"
        + "\"userNick\":\"晴****彦\",\"target\":\"qafollow1\",\"mount\":1,\"createTime\":\"2017-06-13 14:05:24.0\"}],"
        + "\"totalRows\":16,\"partNum\":16}}";

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        System.out.println("============================================");
        ResultBean resultBean = gson.fromJson(resultStr, ResultBean.class);
        System.out.println(resultBean.getInfo().getCode() + "\t" + resultBean.getInfo().getMessage());
        List<ResultEntity> list = resultBean.getData().getResult();
        for (ResultEntity entity : list) {
            System.out.println(entity.getTarget() + "\t" + entity.getMount());
        }

        System.out.println("============================================");
        Map<String, Object> map = gson.fromJson(resultStr, new TypeToken<Map<String, Object>>(){}.getType());
        for (Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        System.out.println("============================================");
        JSONObject jsonObject = JSON.parseObject(resultStr);
        for (Entry<String, Object> entry : jsonObject.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
