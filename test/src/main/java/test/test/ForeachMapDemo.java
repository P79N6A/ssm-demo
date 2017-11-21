package test.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fc.bean.TestBean;
import com.fc.constant.NormalNumberConstant;

/**
 * 比较map遍历的几种方式的效率
 * @author fangcong
 *
 */
public class ForeachMapDemo {

	/**
	 * keySet 遍历方式
	 * @param map
	 * @return
	 */
	public static long forUseTime(Map<Long,Integer> map){
		List<TestBean> list = new ArrayList<TestBean>();
		long startTime = Calendar.getInstance().getTimeInMillis();
		for (long key : map.keySet()){
			TestBean bean = new TestBean();
			bean.setStartId(key);
			bean.setId(map.get(key));
			bean.setEndStr(key + ":" + map.get(key));
			list.add(bean);
		}
		Collections.shuffle(list);
		long endTime = Calendar.getInstance().getTimeInMillis();
		return endTime - startTime;
	}
	
	/**
	 * entrySet 遍历方式
	 * @param map
	 * @return
	 */
	public static long forEntrtSet(Map<Long,Integer> map){
		List<TestBean> list = new ArrayList<TestBean>();
		long startTime = Calendar.getInstance().getTimeInMillis();
		for (Entry<Long, Integer> entry : map.entrySet()){
			TestBean bean = new TestBean();
			bean.setStartId(entry.getKey());
			bean.setId(entry.getValue());
			bean.setEndStr(entry.getKey() + ":" + entry.getValue());
			list.add(bean);
		}
		Collections.shuffle(list);
		long endTime = Calendar.getInstance().getTimeInMillis();
		return endTime - startTime;
	}
	
	public static void main(String[] args) {
		Map<Long,Integer> maps = new HashMap<>(16);
		for(int i = 1; i < NormalNumberConstant.INT_100; i++){
			maps.put(Long.parseLong(i + ""), i);
		}
		long time1 = forUseTime(maps);
		long time2 = forEntrtSet(maps);
		System.out.println(time1);
		System.out.println(time2);
	}
}
