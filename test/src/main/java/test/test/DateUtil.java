package test.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取当前日期，前一天，后一天
 *
 * @author fangcong
 */
public class DateUtil {

    public static String getCurDate() {
        Calendar car = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String time = formater.format(car.getTime());
        return time;
    }

    public static String getPreDate() {
        Calendar car = Calendar.getInstance();
        car.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String time = formater.format(car.getTime());
        return time;
    }

    public static String getNextDate() {
        Calendar car = Calendar.getInstance();
        car.add(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String time = formater.format(car.getTime());
        return time;
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = format.parse("2016-08-25 00:00:01");
        Date date2 = format.parse("2016-08-24 00:00:02");
        int flag = date1.compareTo(date2);
        System.out.println(flag);
    }
}
