package com.memcache;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

/**
 * @author fangcong on 2018/4/26.
 */
public class ConnMemecacheTest {

    public static void main(String[] args) {
        try {
            MemcachedClient client = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
            System.out.println("conn success...");

            //存储数据
            Future future =  client.set("cache", 900, "memcache");

            System.out.println("future get : " + future.get());

            System.out.println("cache get : " + client.get("cache"));

            //add操作不能覆盖旧值，set直接覆盖
            Future future1 = client.add("cache", 900, "redis");
            System.out.println("add flag : " + future1.get());

            Future future2 = client.add("java", 900, "program");
            System.out.println("new key add : " + future2.get());
            System.out.println("new key get : " + client.get("java"));

            //append操作
            Future future3 = client.append("cache", " for store");
            System.out.println("append flag : " + future3.get());
            System.out.println("append get : " + client.get("cache"));

            //对存在的值进行prepend前附加操作
            Future future4 = client.prepend("cache", "use ");
            System.out.println("prepend flag : " + future4.get());
            System.out.println("prepend get : " + client.get("cache"));

            System.out.println("get not exists key : " + client.get("key1"));

            //获取CAS令牌
            CASValue casValue = client.gets("cache");
            System.out.println("casValue : " + casValue);

            //CAS更新数据
            CASResponse casResponse = client.cas("cache", casValue.getCas(), 900, "new cas value");
            System.out.println("casResponse : " + casResponse);
            System.out.println("cas value : " + client.get("cache"));

            //incr
            for (int i = 0; i < 5; i++) {
                long incrNum = client.incr("number", 10, 1, 1000);
                System.out.print(incrNum + " ");
            }

            System.out.println();

            //decr
            for (int i = 0; i < 5; i++) {
                long decrNum = client.decr("number1", 10, 100, 1000);
                System.out.print(decrNum + " ");
            }

            client.shutdown();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
