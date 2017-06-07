package com.fc.games;

/**
 * 开关灯问题
 * @author fangcong
 *
 */
public class ShutDownDemo {

	/**
	 * 第num次操作之后剩余的亮灯数；
	 * 每次操作开或者关num的倍数；
	 * @param num
	 * @return
	 */
	public static int restNum(int[] arr,int num){
		int count = 0;
		//第一次打开全部的灯
		if (num == 1){
			return arr.length;
		}
		for(int i=1;i<=100;i++){
			if (i % num == 0){
				arr[i-1] = arr[i-1]^1;
			}
		}
		for(int i=0;i<arr.length;i++){
			if (arr[i] == 1){
				count ++;
			}
		}
		return count;
	}
	
	/**
	 * 获取num次操作之后的灯亮灭情况
	 * @param num
	 * @return
	 */
	public static int[] restArr(int num){
		int[] array = new int[100];
		if (num == 1){
			for(int i=0;i<100;i++){
				array[i] = 1;
			}
			return array;
		}else{
			array = restArr(num - 1);
			for(int i=1;i<=100;i++){
				if (i % num == 0){
					array[i-1] = array[i-1]^1;
				}
			}
			return array;
		}
	}
	
	public static void main(String[] args) {
		int count = 0;
		int[] array = new int[100];
		array = restArr(4);
		for(int i=0;i<array.length;i++){
			if (array[i] == 1){
				count ++;
			}
		}
		System.out.println(count);
		for(int j=0;j<array.length;j++){
			if(j % 20 == 0){
				System.out.println();
			}
			System.out.print(array[j] + " ");
		}
	}
	
}
