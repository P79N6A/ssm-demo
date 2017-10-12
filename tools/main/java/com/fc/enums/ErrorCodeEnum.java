package com.fc.enums;

/**
 * @author fangcong on 2016/12/12.
 */
public enum ErrorCodeEnum {
    /**
     * 参数错误
     */
    PARAM_ERROR(400,IllegalAccessException.class,"参数错误",true),
    /**
     * 系统错误
     */
    UNKNOWN_ERROR(409,Exception.class,"系统错误",false)
    ;

    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 异常类
     */
    private Class<?> clazz;
    /**
     * 简要错误信息
     */
    private String message;
    /**
     * 是否显示详细错误信息
     */
    private boolean isShow;

    private ErrorCodeEnum(int errorCode, Class<?> clazz, String message, boolean isShow) {
        this.errorCode = errorCode;
        this.isShow = isShow;
        this.message = message;
        this.clazz = clazz;
    }

    public static ErrorCodeEnum getByClass(Class<?> clazz){
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()){
            if (errorCodeEnum.getClazz().equals(clazz)){
                return errorCodeEnum;
            }
        }
        return ErrorCodeEnum.UNKNOWN_ERROR;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getMessage() {
        return message;
    }

    public boolean isShow() {
        return isShow;
    }
}
