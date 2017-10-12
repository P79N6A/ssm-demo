package com.fc.java.improve;

import java.util.HashMap;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlException;

/**
 * Created by fangcong on 2017/3/31.
 */
public class OgnlDemo {

    public static void main(String[] args) throws OgnlException {
        Object exp = Ognl.parseExpression("0<score&&50>score");
        System.out.println(exp);
        Map<String, Object> map = new HashMap<>(16);
        map.put("score", 45);
        Object output = Ognl.getValue(exp, map);
        System.out.println(output);
    }
}
