package test.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

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
        String s = "Dec 16,2017 3:42:22 PM";
        Date d = new Date(s);
        System.out.println(format.format(d));

        Date date1 = format.parse("2018-01-23 00:00:00");
        System.out.println(date1.getTime());
        Date date2 = format.parse("2018-06-05 17:49:00");
        boolean flag = DateUtils.isSameDay(date2, new Date());
        System.out.println("is current day : " + flag);
        System.out.println(date2.getTime());
        int flag1 = date1.compareTo(date2);
        System.out.println(flag1);
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println(curYear);

        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        String begin = "1月3日";
        String end = "1月21日";
        Date endDate = sdf.parse(end);
        Date curDate = sdf.parse("01月15日");
        if (curDate.before(sdf.parse(begin))) {
            System.out.println("not start");
        }
        if (curDate.after(endDate)) {
            System.out.println("end");
        }
        //System.out.println(Runtime.getRuntime().availableProcessors());

    }
}
