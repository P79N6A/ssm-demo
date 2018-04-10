package com.fc.designs.chain;

/**
 * 抽象日志类，定义不同日志级别
 * @author fangcong on 2018/4/10.
 */
public abstract class AbstractLogger {

    public static final Integer DEBUG = 2;

    public static final Integer INFO = 1;

    public static final Integer ERROR = 3;

    /**
     * 责任链中共享的成员变量
     */
    protected Integer level;

    /**
     * 责任链中下一个元素
     */
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    /**
     * 日志记录
     * @param level
     * @param message
     */
    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (null != nextLogger) {
            nextLogger.logMessage(level, message);
        }
    }

    abstract protected void write(String message);
}
