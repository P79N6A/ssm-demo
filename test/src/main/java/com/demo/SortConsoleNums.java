package com.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 控制台中输入一串数字，空格分隔，输出排序后的数字列表<br>
 * 利用TreeSet add()方法进行默认排序
 * @author fangcong
 *
 */
public class SortConsoleNums {

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入一组数：");
		try {
			String str = reader.readLine();
			//比较器构造方法，按指定顺序排序
			Set<Integer> set = new TreeSet<>((o1, o2) -> o1 > o2 ? -1 : 1);
			String[] strs = str.split(",");
			for (String t : strs) {
				set.add(Integer.parseInt(t));
			}
			System.out.println("排序之后的结果是：");
			Iterator<Integer> it = set.iterator();
			while(it.hasNext()){
				System.out.print(it.next() + " ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
