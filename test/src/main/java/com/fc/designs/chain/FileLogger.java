package com.fc.designs.chain;

/**
 * @author fangcong on 2018/4/10.
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("file::logger:" + message);
    }
}
