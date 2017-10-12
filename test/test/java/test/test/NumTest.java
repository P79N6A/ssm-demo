package test.test;

public class NumTest {

	public static void main(String[] args) {
        //直接使用浮点数进行计算，得到的结果是有问题的 
        System.out.println(0.01+0.05); 
        //使用了BigDecimal类进行计算后，可以做到精确计算 
        System.out.println(Arith.add(0.05, 0.01)); 
 }
}
