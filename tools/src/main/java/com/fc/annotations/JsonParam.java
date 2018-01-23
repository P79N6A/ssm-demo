package com.fc.annotations;

import java.lang.annotation.*;

/**
 * Created by fangcong on 2016/12/2.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonParam {
}
