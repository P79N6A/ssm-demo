package com.demo.future;

/**
 * 负责调用client发起请求，返回数据
 * @author fangcong on 2018/4/8.
 */
public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("真实数据：" + data.getResult());
    }
}
