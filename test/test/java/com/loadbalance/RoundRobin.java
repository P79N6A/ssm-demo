package com.loadbalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author fangcong on 2017/5/25.
 * 轮询算法
 */
public class RoundRobin {

    private static Integer pos = 0;

    public static String getServer() {
        //重建map，避免服务器上下线导致并发问题
        Map<String, Integer> map = new HashMap<>(16);
        map.putAll(IpMap.serverWeightMap);

        //取得IP地址list
        List<String> list = new ArrayList<>();
        Set<String> set = map.keySet();
        list.addAll(set);

        String server;
        synchronized (pos) {
            if (pos >= set.size()) {
                pos = 0;
            }
            server = list.get(pos);
            pos++;
        }
        return server;
    }
}
