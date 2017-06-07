package com.fc.common;

import java.util.regex.Pattern;

/**
 * Created by fangcong on 2017/3/6.
 * 常用正则表达式
 */
public class RegexUtils {

    /**
     * 日期格式校验
     * 2016-1-1; 2016-11-11 <br>
     * 2016/1/1; 2016/11/11 <br>
     * 07-1-1; 07-01-01; <br>
     */
    private static final String DATE_REG = "^(\\d{1,4})(-|\\/)(\\d{1,2})\\2(\\d{1,2})$";

    /**
     * 校验密码强度(not passed)
     * 包含大小写字母和数字，不能使用特殊字符，长度8-10位
     */
    private static final String PASSWORD_REG = "(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}";

    /**
     * 校验中文
     */
    private static final String CHINESE_REG = "^[\\u4e00-\\u9fa5]{0,}$";

    /**
     * 校验输入是否符合规范
     * @param regex 正则表达式串
     * @param input 输入串
     * @return      true/false
     */
    public static boolean checkRegex(String regex, String input){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).find();
    }

    public static void main(String[] args){
        String date = "2017-03-06";
        System.out.println(checkRegex(DATE_REG, date));
        String password = "123abcEDD";
        System.out.println(checkRegex(PASSWORD_REG, password));
    }
}
