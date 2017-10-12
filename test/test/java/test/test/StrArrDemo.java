package test.test;

public class StrArrDemo {

	public static void main(String[] args) {
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
