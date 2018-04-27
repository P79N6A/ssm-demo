package com.memcache;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;

import net.spy.memcached.MemcachedClient;

/**
 * @author fangcong on 2018/4/27.
 */
public class SysMemcacheOpt {

    public static void main(String[] args) {
        MemcachedClient client = null;
        try {
            InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 11211);
            client = new MemcachedClient(socketAddress);
            Map<SocketAddress, String> map = client.getVersions();
            String version = map.get(socketAddress);
            System.out.println("version : " + version);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != client) {
                client.shutdown();
            }
        }
    }
}
