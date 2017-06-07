package com.loadbalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @author fangcong on 2017/5/25.
 * 随机法
 */
public class RandomDemo {

    public static String getServer() {
        Map<String, Integer> map = new HashMap<>(16);
        map.putAll(IpMap.serverWeightMap);

        ArrayList<String> servers = new ArrayList<>();
        Set<String> keySet = map.keySet();
        servers.addAll(keySet);

        Random random = new Random();
        String server = servers.get(random.nextInt(servers.size()));
        return server;
    }
}
