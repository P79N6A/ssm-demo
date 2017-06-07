package com.loadbalance;

import java.util.ArrayList;

/**
 * @author fangcong on 2017/5/25.
 */
public class WeightRandom {

    public static String getServer() {
        ArrayList<String> serverList = IpMap.getServerList();

        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(serverList.size());

        return serverList.get(randomPos);
    }
}
