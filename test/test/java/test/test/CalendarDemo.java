package test.test;

import java.util.Calendar;  
import java.util.GregorianCalendar;  
  
public class CalendarDemo {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
    	Calendar calendar = new GregorianCalendar();
    	int day = calendar.get(Calendar.DATE);
    	int minute = calendar.get(Calendar.MINUTE);
    	int hour = calendar.get(Calendar.HOUR_OF_DAY);
    	System.out.println("time:" + day + " minute:" + minute + " hour:" + hour);
        //获得一个日历对象  
        Calendar ca = Calendar.getInstance();  
        //创建日历对象时间为2012.12.12.12,12,12  
        Calendar c2 = new GregorianCalendar(2012, 11, 12, 12, 12, 12);  
        //disPlay(ca);  
          
        //根据在年中的天数,求相隔天数。  
        int days = c2.get(Calendar.DAY_OF_YEAR)-ca.get(Calendar.DAY_OF_YEAR);  
        System.out.println("年中的天数c2：" + c2.get(Calendar.DAY_OF_YEAR));
        System.out.println("年中的天数ca: " + ca.get(Calendar.DAY_OF_YEAR));
        System.out.println("相差"+days+"天");  
          
        //重新设定c2对象的时间  
        System.out.println("+++++++++++++++++重新设定后时间++++++++++++：");
        //月份和日期都是0开头。月份中0表示一月。月中的日期，0表示一日。
        c2.set(2012,11,23);
        c2.set(Calendar.HOUR, 22);  
        c2.set(Calendar.MINUTE,12);  
        c2.set(Calendar.SECOND, 12);  
        //disPlay(c2);  
          
        System.out.println("+++++++++++++++++修改后时间++++++++++++：");  
        //向后延长29天  
        ca.add(Calendar.DAY_OF_YEAR,29);  
        //disPlay(ca);  
        //再延长4小时  
        ca.add(Calendar.HOUR, 4);  
        //disPlay(ca);  
  
    }  
  
    public static void disPlay(Calendar ca) {  
        String []mon = {"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};    
        String []week = {"","星期日","星期一","星期二","星期三","星期四","星期五","星期六"};  
        System.out.print(ca.get(Calendar.YEAR)+"年");  
        System.out.print(mon[ca.get(Calendar.MONTH)]);  
        System.out.print(ca.get(Calendar.DAY_OF_MONTH)+"日");  
        System.out.println(week[ca.get(Calendar.DAY_OF_WEEK)]);  
        System.out.print("时间：");  
        System.out.print(ca.get(Calendar.HOUR_OF_DAY)+":");  
        System.out.print(ca.get(Calendar.MINUTE)+":");  
        System.out.println(ca.get(Calendar.SECOND));  
          
          
    }  
  
} 
