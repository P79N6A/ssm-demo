package com.fc.work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.fc.bean.CouponVO;
import com.fc.bean.FormInfo;
import com.fc.bean.FormResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * fastJson常用api测试
 *
 * @author fangcong on 2017/1/16.
 */
public class ParseJsonStr {

    /**
     * json数组，可转成List<?>
     */
    private static final String JSON_ARRAY_OBJ_STR = "[{\"sellerId\":\"123333\",\"activityId\":\"1111\"},{\"sellerId\":\"1112\",\"activityId\":\"sdaas1\"}]";

    /**
     * json串，可转map
     */
    private static final String JSON_NORMAL_STR = "{\"name\":\"zhangsan\",\"beginDate\":\"2018-01-01\",\"phone\":\"18752143266\"}";

    /**
     * json串，可转bean
     */
    private static final String JSON_BEAN_STR = "{\"sellerId\":\"123456\",\"activityId\":\"adns45222sa\"}";

    private static final String JSON_TEST_STR = "{reportTime: \"1532596707397\", "
        + "reportType: 1, handledText: { deviceMac: \"AC-B5-7D-9C-77-71\", "
        + "deviceType: \"GATEWAY\", deviceGatewayMac: \"AC-B5-7D-9C-77-71\", "
        + "deviceName: \"家里的网关\", nodeNum: 1 }, permission: "
        + "\"e10adc3949ba59abbe56e057f20f883e\"}";

    public static void main(String[] args){
        List<CouponVO> list = JSONArray.parseArray(JSON_ARRAY_OBJ_STR, CouponVO.class);
        for (CouponVO couponVO : list) {
            System.out.println(couponVO.getSellerId() + "--" + couponVO.getActivityId());
        }
        System.out.println("--------------------------");

        JSONObject jsonObject = JSON.parseObject(JSON_NORMAL_STR);
        for (String key : jsonObject.keySet()) {
            System.out.println(key + " : " + jsonObject.getString(key));
        }
        System.out.println("--------------------------");

        JSONObject jsonObject1 = JSON.parseObject(JSON_TEST_STR);
        for (String key : jsonObject1.keySet()) {
            System.out.println(key + " : " + jsonObject1.getString(key));
        }
        System.out.println("--------------------------");

        CouponVO couponVO = JSON.parseObject(JSON_BEAN_STR, CouponVO.class);
        System.out.println(couponVO.toString());
        System.out.println("--------------------------");

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Map<String, Object> map = gson.fromJson(JSON_TEST_STR, new TypeToken<Map<String, Object>>(){}.getType());
        for (String key : map.keySet()){
            Object value = map.get(key);
            System.out.println(key + " : " + value);
        }

        for (Map.Entry<String, Object> entry: map.entrySet()){
            System.out.print(entry.getValue() + ",");
        }
        List<FormInfo> list1 = new ArrayList<>();
        FormInfo info1 = new FormInfo(3469, "mini1", "phone", "123456789");
        list1.add(info1);
        info1 = new FormInfo(3469, "mini1", "address", "aasada");
        list1.add(info1);
        FormInfo info2 = new FormInfo(3450, "mini2", "phone", "23322212");
        list1.add(info2);
        info2 = new FormInfo(3450, "mini2", "address", "ssdddss");
        list1.add(info2);
        List<FormResult> formResults = convertResult(list1);
        for (FormResult result : formResults) {
            System.out.println(result.toString());
        }
    }

    private static List<FormResult> convertResult(List<FormInfo> list) {
        Map<Long, FormResult> formmap = new HashMap<>(16);
        for (int i = 0; i < list.size(); i++) {
            FormInfo info = list.get(i);
            FormResult result = new FormResult();
            result.setInfoId(info.getInfoId());
            result.setUserNick(info.getUserNick());
            Map<String, Object> map = new HashMap<>(16);
            map.put(info.getDataKey(), info.getDataValue());
            if (!formmap.containsKey(info.getInfoId())) {
                result.setInfos(map);
                formmap.put(info.getInfoId(), result);
            } else {
                result = formmap.get(info.getInfoId());
                result.getInfos().putAll(map);
            }
        }
        return new ArrayList<>(formmap.values());
    }
}
