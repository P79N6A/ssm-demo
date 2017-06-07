package test.test;
/**
 * 多层嵌套For循环优化
 * @author fangcong
 *
 */
public class MultiForImprove {

	public static void testFunction(int i,int j,int k){
		System.out.print("");
	}
	
	/**
	 * 原始for循环，待优化
	 */
	public static void test(){
		long startTime = System.nanoTime();
		for(int i = 0;i < 1000;i++){
			for(int j = 0;j < 100;j++){
				for(int k = 0;k < 10;k++){
					testFunction(i,j,k);
				}
			}
		}
		long endTime = System.nanoTime();
		System.out.println("useTime=" + (endTime - startTime));
	}
	
	/**
	 * 优化方案1
	 */
	public static void testA(){
		long startTime = System.nanoTime();
		for(int i = 0;i < 10;i++){
			for(int j = 0;j < 100;j++){
				for(int k = 0;k < 1000;k++){
					testFunction(i,j,k);
				}
			}
		}
		long endTime = System.nanoTime();
		System.out.println("useTimeA=" + (endTime - startTime));
	}
	
	/**
	 * 优化方案2
	 */
	public static void testB(){
		long startTime = System.nanoTime();
		int i,j,k;
		for(i = 0;i < 1000;i++){
			for(j = 0;j < 100;j++){
				for(k = 0;k < 10;k++){
					testFunction(i,j,k);
				}
			}
		}
		long endTime = System.nanoTime();
		System.out.println("useTimeB=" + (endTime - startTime));
	}
	
	public static void main(String[] args) {
		test();
		testA();
		testB();
	}
}
