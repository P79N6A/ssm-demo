package com.fc.java.improve;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fangcong on 2017/3/30.
 */
public class TestReflect {

    private String filed1;

    public void reflect1() {
        System.out.println("test reflect method");
    }

    public void reflect2(String name, Integer age) {
        System.out.println("test reflect method with params:");
        System.out.println("name = " + name + " age = " + age);
    }

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.fc.java.improve.TestReflect");
        Method[] methods = clazz.getDeclaredMethods();
        //调用reflect1方法
        Method method1 = clazz.getMethod("reflect1");
        method1.invoke(clazz.newInstance());
        //调用reflect2方法
        Method method2 = clazz.getMethod("reflect2", String.class, Integer.class);
        method2.invoke(clazz.newInstance(), "zhangsan", 22);
        //获取属性
        Field field1 = clazz.getDeclaredField("filed1");
        Object obj = clazz.newInstance();
        field1.setAccessible(true);
        field1.set(obj, "test");
        System.out.println(field1.get(obj));

        /*String curDate = DateUtils.getFormatDate_CN(new Date());
        String curMonthDay = DateUtils.getFormatMonthDay_CN(new Date());
        System.out.println(curDate);
        System.out.println(curMonthDay);

        String str = getActivityStatus("2017-07-27", "2017-08-21");
        String str1 = DateUtils.getDateFormat_MDCN("2017-07-27");
        String str2 = DateUtils.getDateFormat_MDCN("2017-08-21");
        System.out.println(str1 + "--" + str2);
        System.out.println(str);*/

        /*System.out.println("====================");
        String str3 = DateUtils.getFormatDay(new Date());
        String date = DateUtils.getFormatDate(DateUtils.getFormatDate("20170803", "yyyyMMdd"), "yyyy年MM月dd日");
        System.out.println("date = " +date);*/

        System.out.println(System.getProperty("user.dir"));
        System.out.println(File.separator);
        String newStr = convertDateStr("20170802", "yyyyMMdd", "yyyy年MM月dd日");
        System.out.println(newStr);
    }

    public static String convertDateStr(String currDateStr, String oldPattern, String newPattern) {
        SimpleDateFormat sdf;
        try {
            sdf = new SimpleDateFormat(oldPattern);
            Date dateStr = sdf.parse(currDateStr);
            sdf = new SimpleDateFormat(newPattern);
            return sdf.format(dateStr);
        } catch (Exception e) {
            return currDateStr;
        }
    }

    public static String getActivityStatus(String begin, String end) {
        begin = begin.substring(begin.indexOf("-") + 1).replace("-", "月") + "日";
        end = end.substring(end.indexOf("-") + 1).replace("-", "月") + "日";
        System.out.println(begin + "-" +end);
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        try {
            Date beginDate = sdf.parse(begin);
            Date endDate = sdf.parse(end);
            Date curDate = sdf.parse(sdf.format(new Date()));
            if (curDate.before(beginDate)) {
                return "before";
            } else if (curDate.after(endDate)) {
                return "end";
            } else {
                return "now";
            }
        } catch(ParseException px) {
            px.printStackTrace();
        }
        return null;
    }
}
