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
        Date date1 = format.parse("2018-02-27 00:00:00");
        System.out.println(date1.getTime());
        System.out.println(new Date().getTime());
        Date date2 = format.parse("2018-03-16 00:00:00");
        int flag = date1.compareTo(date2);
        System.out.println(flag);
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
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
