package test.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {
		Timer timer = new Timer();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			Date d = format.parse("2016/08/17 14:36:30");
			timer.schedule(new TimerTask() {
				int i = 1;
				@Override
				public void run() {
					System.out.println("第" + i + "次执行!");
					i++;
				}
			}, d, 3000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
