package com.fc.designs.chain;

/**
 * @author fangcong on 2018/4/10.
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("error:logger:" + message);
    }
}
