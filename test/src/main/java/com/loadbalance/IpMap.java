package com.loadbalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author fangcong on 2017/5/25.
 */
public class IpMap {

    /**
     * 定义待路由的IP列表和权重map
     */
    public static Map<String, Integer> serverWeightMap = new HashMap<>();

    static {
        serverWeightMap.put("192.168.1.100", 1);
        serverWeightMap.put("192.168.1.101", 1);

        serverWeightMap.put("192.168.1.102", 4);
        serverWeightMap.put("192.168.1.103", 1);

        serverWeightMap.put("192.168.1.104", 2);
        serverWeightMap.put("192.168.1.105", 1);

        serverWeightMap.put("192.168.1.106", 3);
        serverWeightMap.put("192.168.1.107", 4);

        serverWeightMap.put("192.168.1.108", 1);
        serverWeightMap.put("192.168.1.109", 2);

        serverWeightMap.put("192.168.1.110", 1);
        serverWeightMap.put("192.168.1.111", 3);
    }

    public static ArrayList<String> getServerList() {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<>(16);
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();

        ArrayList<String> serverList = new ArrayList<>();
        while (iterator.hasNext())
        {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                serverList.add(server);
            }
        }
        return serverList;
    }
}
