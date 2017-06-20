package com.loadbalance;

import java.util.ArrayList;

/**
 * @author fangcong on 2017/5/25.
 */
public class WeightRoundRobin {

    private static Integer pos;

    public static String getServer() {
        ArrayList<String> serverList = IpMap.getServerList();

        String server;
        synchronized (pos)
        {
            if (pos >= serverList.size()) {
            pos = 0;
        }
            server = serverList.get(pos);
            pos ++;
        }
        return server;
    }
}
