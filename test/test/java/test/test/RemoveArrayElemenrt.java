package test.test;

import java.util.Arrays;

/**
 * 删除数组元素
 */
public class RemoveArrayElemenrt {

	public static void main(String[] args) {

		String[] arr = {"aa", "bb", "cc", "dd"};

		int length = arr.length;
		for (int i = 0; i < length; i++) {
			if ("bb".equals(arr[i])) {
				System.arraycopy(arr, i + 1, arr, i, length - i - 1);
				arr[--length] = null;
				arr = Arrays.copyOf(arr, length);
			}
		}

		System.out.println(arr.length);
		for (int k = 0; k < arr.length; k++) {
			System.out.println(arr[k] + "\t");
		}
	}

}
