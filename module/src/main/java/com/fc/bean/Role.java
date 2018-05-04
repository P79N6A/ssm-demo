package com.fc.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author fangcong on 2018/5/4.
 */
@Setter@Getter
@ToString
public class Role {

    private Long id;

    private String roleName;

    private String roleType;
}
