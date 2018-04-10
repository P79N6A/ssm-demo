package com.demo.future;

/**
 * 最终需要使用的数据模型，构造很慢
 *
 * @author fangcong on 2018/4/8.
 */
public class RealData implements Data {

    protected final String result;

    public RealData(String param) {
        //构造可能比较慢，这里用sleep模拟
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(param);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
