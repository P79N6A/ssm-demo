package test.test;

import java.text.DecimalFormat;

public class StrArrDemo {

	public static void main(String[] args) {
		/*String[] str = new String[2];
		if(str[0] == null){
			System.out.println(true);
		}
		System.out.println(str[0]);*/
		/*DecimalFormat df = new DecimalFormat("0"); 
		String storeId = "6.9223906E7";
		String value = df.format(storeId);
		System.out.println(value);*/
		Long num = null;
		try{
			String str = String.valueOf(num);
			System.out.println("str=" + str);
		}catch(Exception e){
			System.out.println("参数不能为空：");
			e.printStackTrace();
		}
	}
}
