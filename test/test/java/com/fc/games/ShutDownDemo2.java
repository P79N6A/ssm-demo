package com.fc.games;
/**
 * 150全部亮着的灯，先拉下3的倍数的开关，再拉下5的倍数的开关，
 * 求剩余亮着的灯的数量
 * @author fangcong
 *
 */
public class ShutDownDemo2 {

	public static void initArr(int[] arr){
		for (int i = 0;i < arr.length;i++){
			arr[i] = 1;
		}
	}
	
	/**
	 * 返回关闭相应倍数后剩余的情况
	 * @param arr
	 * @param num 倍数
	 * @return
	 */
	public static int[] restArr(int[] arr,int num){
		//不需要处理的情况
		if (num == 1 || num <= 0 || num >= arr.length){
			return arr;
		}
		//需要做处理
		for(int i=1;i<=arr.length;i++){
			if (i % num == 0){
				arr[i-1] = arr[i-1]^1;
			}
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int count = 0;
		int[] arrs = new int[150];
		initArr(arrs);
		int[] arr = restArr(restArr(arrs, 3), 5);
		for (int i = 0;i < arr.length;i++){
			if (arr[i] == 1){
				count++;
			}
		}
		System.out.println("count=" + count);
	}
}
