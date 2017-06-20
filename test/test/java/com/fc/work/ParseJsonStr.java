package com.fc.work;

import com.alibaba.fastjson.JSONArray;
import com.fc.bean.CouponVO;
import com.fc.bean.FormInfo;
import com.fc.bean.FormResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fangcong on 2017/1/16.
 */
public class ParseJsonStr {

    public static void main(String[] args){
        String jsonStr = "[{\"sellerId\":\"123333\",\"activityId\":\"1111\"},{\"sellerId\":\"1112\",\"activityId\":\"sdaas1\"}]";
        List<CouponVO> list = JSONArray.parseArray(jsonStr, CouponVO.class);
        for (CouponVO couponVO : list) {
            System.out.println(couponVO.getSellerId() + "--" + couponVO.getActivityId());
        }
        /*String jsonStr = "{\"name\":\"zhangsan\",\"address\":\"hangzhou\",\"phone\":\"18752143266\"}";
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Map<String, Object> map = gson.fromJson(jsonStr,new TypeToken<Map<String, Object>>(){}
                .getType());
        for (String key : map.keySet()){
            String value = (String)map.get(key);
            System.out.println(key + " : " + value);
        }

        for (Map.Entry<String, Object> entry: map.entrySet()){
            System.out.print(entry.getValue() + ",");
        }*/
        /*List<FormInfo> list = new ArrayList<>();
        FormInfo info1 = new FormInfo(3469, "mini1", "phone", "123456789");
        list.add(info1);
        info1 = new FormInfo(3469, "mini1", "address", "aasada");
        list.add(info1);
        FormInfo info2 = new FormInfo(3450, "mini2", "phone", "23322212");
        list.add(info2);
        info2 = new FormInfo(3450, "mini2", "address", "ssdddss");
        list.add(info2);
        List<FormResult> formResults = convertResult(list);
        for (FormResult result : formResults) {
            System.out.println(result.toString());
        }*/
    }

    private static List<FormResult> convertResult(List<FormInfo> list) {
        Map<Long, FormResult> formmap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            FormInfo info = list.get(i);
            FormResult result = new FormResult();
            result.setInfoId(info.getInfoId());
            result.setUserNick(info.getUserNick());
            Map<String, Object> map = new HashMap<>();
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
