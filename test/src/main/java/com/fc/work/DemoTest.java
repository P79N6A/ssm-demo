package com.fc.work;

import com.fc.bean.Person;

/**
 * Created by fangcong on 2016/12/28.
 */
public class DemoTest {

    public static void main(String[] args) {

        /*Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.print(hour);*/

       /* Map<String,Object> map = new HashMap<>();
        map.put("prize",11770035L);
        System.out.print(map.get("prize").equals(11770035L));*/
       /* String bk = "ab,dd;cc,ee;ad,sd,fg";
        String[] str = bk.split(";");
        for (int i = 0;i < str.length;i++){
            System.out.println(str[i]);
        }*/
        Person person = new Person();
        String className = person.getClass().getName();
        System.out.println(className);
    }
}
