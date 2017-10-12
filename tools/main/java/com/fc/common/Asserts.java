package com.fc.common;

import java.util.Collection;

import com.fc.exception.IllegalArgumentException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 校验参数
 * Created by fangcong on 2016/12/9.
 */
public class Asserts {

    protected static final Logger logger = LoggerFactory.getLogger(Asserts.class);

    public Asserts(){
    }

    /**
     * 校验所有的参数都不允许为空
     * @param errorMsg
     * @param objs
     */
    public static void allCanNotNull(String errorMsg, Object... objs){
        if (objs != null && objs.length > 0){
            boolean hasNull = false;
            Object[] arr = objs;
            int length = objs.length;

            for (int i = 0;i < length; ++i){
                Object obj = arr[i];
                if (obj instanceof String){
                    if (StringUtils.isBlank((String)obj)){
                        hasNull = true;
                        break;
                    }
                }else if (obj instanceof Collection){
                    if (CollectionUtils.isEmpty((Collection) obj)){
                        hasNull = true;
                        break;
                    }
                }else if (obj instanceof Object[]){
                    if (((Object[])obj).length <= 0){
                        hasNull = true;
                        break;
                    }
                }else if (obj == null){
                    hasNull = true;
                    break;
                }
            }

            if (hasNull){
                throw new IllegalArgumentException("201_PARAMATERS_ERROR",errorMsg);
            }
        }else {
            throw new IllegalArgumentException("201_PARAMATERS_ERROR",errorMsg);
        }
    }

    /**
     * 参数校验，至少有一个参数不为空
     * @param errorMsg
     * @param objs
     */
    public static void anyCanNull(String errorMsg,Object... objs){
        if (objs != null && objs.length > 0){
            boolean hasNotNull = false;
            Object[] arr = objs;
            int length = objs.length;
            for (int i = 0;i < length; ++i){
                Object obj = arr[i];
                if (obj instanceof String){
                    if (StringUtils.isNotBlank((String)obj)){
                        hasNotNull = true;
                        break;
                    }
                }else if (obj instanceof Collection){
                    if (CollectionUtils.isNotEmpty((Collection)obj)){
                        hasNotNull = true;
                        break;
                    }
                }else if (obj instanceof Object[]){
                    if (((Object[]) obj).length > 0){
                        hasNotNull = true;
                        break;
                    }
                }else if (obj != null){
                    hasNotNull = true;
                    break;
                }
            }

            if (!hasNotNull){
                throw new IllegalArgumentException("201_PARAMATERS_ERROR",errorMsg);
            }
        }else {
            throw new IllegalArgumentException("201_PARAMATERS_ERROR",errorMsg);
        }
    }

    /**
     * 所有条件都必须为真
     * @param errorMsg
     * @param condition
     */
    public static void allCanNotFalse(String errorMsg,Boolean... condition){
        if (condition != null && condition.length > 0){
            Boolean[] arr = condition;
            int length = condition.length;
            for (int i = 0;i < length;++i){
                Boolean obj = arr[i];
                if (!obj.booleanValue()){
                    throw new IllegalArgumentException("500_E_INVALID",errorMsg);
                }
            }
        }else{
            throw new IllegalArgumentException("201_PARAMATERS_ERROR",errorMsg);
        }
    }

    /**
     * 只有有一个条件为真
     * @param errMsg
     * @param condition
     */
    public static void anyCanTrue(String errMsg, Boolean... condition) {
        if(condition != null && condition.length > 0) {
            boolean hasTrue = false;
            Boolean[] arr = condition;
            int len = condition.length;

            for(int i = 0; i < len; ++i) {
                Boolean bool = arr[i];
                if(bool.booleanValue()) {
                    hasTrue = true;
                    break;
                }
            }

            if(!hasTrue) {
                throw new IllegalArgumentException("500_E_INVALID", errMsg);
            }
        } else {
            throw new IllegalArgumentException("201_E_PARAMETER_IS_NULL", errMsg);
        }
    }
}
