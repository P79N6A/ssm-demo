package com.fc.exception;

/**
 * 参数异常
 * @author fangcong on 2016/12/9.
 */
public class IllegalArgumentException extends RuntimeException {

    private static final long serialVersionUID = 7536760154236444335L;

    private String errorCode;
    private String errorName;

    public IllegalArgumentException() {
    }

    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(Throwable cause, String errorName) {
        super(cause);
        this.errorName = errorName;
    }

    public IllegalArgumentException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public IllegalArgumentException(Throwable cause, String errorCode, String message) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public IllegalArgumentException(String msg,Exception e){
        super(msg,e);
        this.errorName = msg;
    }

    public IllegalArgumentException(String message,Throwable cause){
        super(message,cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorName() {
        return errorName;
    }
}
