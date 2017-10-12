package com.fc.common;

import java.io.Serializable;

/**
 * @author fangcong on 2016/12/2.
 */
public class AjaxResult<T> implements Serializable{

    private static final long serialVersionUID = -5603215984322606739L;

    private boolean success;
    private String errorCode;
    private String msg;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static <T> AjaxResult<T> getSuccessResult(){
        AjaxResult<T> result = new AjaxResult<>();
        result.setSuccess(true);
        result.setMsg("S001");
        return result;
    }

    public static <T> AjaxResult<T> getSuccessResult(T v){
        AjaxResult<T> result = new AjaxResult<>();
        result.setSuccess(true);
        result.setData(v);
        return result;
    }

    public static <T> AjaxResult<T> getFailResult(String errorCode,String msg){
        AjaxResult<T> result = new AjaxResult<>();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setMsg(msg);
        return result;
    }
}
