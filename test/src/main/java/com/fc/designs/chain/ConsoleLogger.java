package com.fc.designs.chain;

/**
 * 扩展类
 * @author fangcong on 2018/4/10.
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("console::logger:" + message);
    }
}
