package com.fc.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author SYSTEM on 2017/11/9.
 */
@Getter@Setter
public class TestLombookBean {
    
    @Accessors(prefix = {"first"}, fluent = true, chain = false)
    private String firstName;

    private Integer age;
}
