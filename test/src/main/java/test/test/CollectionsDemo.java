package test.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import com.fc.bean.Person;

/**
 * 集合工具类的使用示例
 * @author SYSTEM
 *
 */
public class CollectionsDemo {

	/**
	 * Collections 类的默认排序和实现比较器排序呢
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private static void sort(){
		double[] array = {121,111,23,256,156};
		List<Double> list = new ArrayList<Double>();
		for(int i=0;i<array.length;i++){
			list.add(array[i]);
		}
		//Collections.sort(list);
		Collections.sort(list, new Comparator<Double>() {
			@Override
			public int compare(Double d1, Double d2) {
				if (d1 < d2) {
					return 1;
				}
				return -1;
			}
			
		});
		for (Double double1 : list) {
			System.out.print(double1 + " ");
		}
		
		Person[] person = new Person[] {  
		         new Person("ouyang", "feng", Boolean.TRUE, new Integer(27)),  
		         new Person("zhuang", "gw", Boolean.TRUE, new Integer(27)),  
		         new Person("zhuang", "gw", Boolean.FALSE, new Integer(27)),  
		         new Person("zhuang", "gw", Boolean.FALSE, new Integer(2)),  
		     };  
	     for (int i = 0; i < person.length; i++) {  
	       System.out.println("before sort=" + person[i]);  
	     }  
	     java.util.Arrays.sort(person, Comparators.getComparator());  
	     for (int i = 0; i < person.length; i++) {  
	        System.out.println("after sort=" + person[i]);  
	     }
	}
	
	/**
	 * Collections类的混排，随机顺序
	 */
	private static void shufulling(){
		List<Double> list = new ArrayList<Double>();
		double array[] = {112, 111, 23, 456, 231 };
		for (int i = 0; i < array.length; i++) {
			list.add(new Double(array[i]));
		}
		Collections.shuffle(list);
		for (int i = 0; i < array.length; i++) {
		  System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}
	
	/**
	 * 反转，按自然顺序的倒序进行排列
	 */
	private static void reverse(){
		List<Double> list = new ArrayList<Double>();
		double array[] = {112, 111, 23, 456, 231 };
		for (int i = 0; i < array.length; i++) {
			list.add(new Double(array[i]));
		}
		Collections.reverse(list);
		for (int i = 0; i < array.length; i++) {
		  System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}
	
	/**
	 * 用指定元素替换列表中的所有元素
	 */
	private static void fill(){
		List<Double> list = new ArrayList<Double>();
		double array[] = {112, 111, 23, 456, 231 };
		for (int i = 0; i < array.length; i++) {
			list.add(new Double(array[i]));
		}
		Collections.fill(list, Double.valueOf(100));
		for (int i = 0; i < array.length; i++) {
		  System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}
	
	/**
	 * 将源list拷贝到目标list，长度小于目前list则超过部分不覆盖
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void copy(){
		double array[] = {112, 111, 23, 456, 231 };
		List list = new ArrayList();
		List li = new ArrayList();
		for (int i = 0; i < array.length; i++) {
			list.add(new Double(array[i]));
		}
		double arr[] = {1131,333};
		for(int j=0;j<arr.length;j++){
			li.add(new Double(arr[j]));
		}
		Collections.copy(list,li);
		for (int i = 0; i <list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}
	
	/**
	 * list中最小元素和最大元素
	 */
	private static void minAndMax(){
		List<Double> list = new ArrayList<Double>();
		double array[] = {112, 111, 23, 456, 231 };
		for (int i = 0; i < array.length; i++) {
			list.add(new Double(array[i]));
		}
	}

	/**
	 * 使用treeSet进行排序
	 */
	private static void treeSetSort(){
		double[] array = {112, 111, 23, 45, 37, 231, 125};
		TreeSet<Double> set = new TreeSet<>(new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if (o1 instanceof Double){
					if ((Double)o1 > (Double)o2){
						return 1;
					}
					return -1;
				}
				return 0;
			}
		});
		for (int i = 0;i < array.length;i++){
			set.add(array[i]);
		}
		for (Double e : set){
			System.out.print(e + "\t");
		}
	}

	public static void main(String[] args) {
		/*shufulling();
		reverse();
		fill();
		copy();
		minAndMax();*/
		treeSetSort();
	}
}
