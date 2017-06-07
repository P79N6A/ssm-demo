package com.fc.games;

import java.util.Random;
import java.util.Scanner;

import com.fc.enums.SmallGameEnum;

/**
 * 简单的人机互动猜拳游戏
 * @author fangcong
 *
 */
public class CaiQuanGame {

	/**
	 * 根据输入的数字输出描述
	 * @param num
	 */
	private static void printInput(String text,Integer num){
		if (num.equals(SmallGameEnum.STONE.getValue())){
			System.out.println(text + "出的是：" + SmallGameEnum.STONE.getDesc());
		}else if (num.equals(SmallGameEnum.SCISSORS.getValue())){
			System.out.println(text + "出的是：" + SmallGameEnum.SCISSORS.getDesc());
		}else if (num.equals(SmallGameEnum.CLOTH.getValue())){
			System.out.println(text + "出的是：" + SmallGameEnum.CLOTH.getDesc());
		}else{
			System.out.println(text + "出的不符合规范!");
		}
	}
	
	/**
	 * 判断输赢关系
	 * @param num1 你出的
	 * @param num2 电脑出的
	 * @return
	 */
	private static String compareNum(Integer num1,Integer num2){
		if (num1.equals(num2)){
			return "平局";
		}
		if (num1.equals(SmallGameEnum.STONE.getValue())){
			if (num2.equals(SmallGameEnum.SCISSORS.getValue())){
				return "你赢了";
			}
			return "电脑赢了";
		}
		if (num1.equals(SmallGameEnum.SCISSORS.getValue())){
			if (num2.equals(SmallGameEnum.CLOTH.getValue())){
				return "你赢了";
			}
			return "电脑赢了";
		}
		if (num1.equals(SmallGameEnum.CLOTH.getValue())){
			if (num2.equals(SmallGameEnum.STONE.getValue())){
				return "你赢了";
			}
			return "电脑赢了";
		}
		return "游戏错误！";
	}
	
	/**
	 * 获取输入，电脑随机生成，然后对比给出结果
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.print("请出拳，0石头，1剪刀，2布：");
		Scanner c = new Scanner(System.in);
		Integer num = c.nextInt();
		printInput("你",num);
		if (SmallGameEnum.getEnumByValue(num) != null){
			Random random = new Random();
			Integer rNum = random.nextInt(SmallGameEnum.values().length);
			printInput("电脑",rNum);
			String result = compareNum(num,rNum);
			System.out.println(result);
		}
		c.close();
	}
}
