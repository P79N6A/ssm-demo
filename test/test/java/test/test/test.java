package test.test;

public class test {
	
	public static void main(String[] args) {
		/*int i;
		String str = Long.toString(54,2);
		System.out.println(str);
		if (str.indexOf("0") != -1){
			i = str.indexOf("0");
		}else{
			i = str.lastIndexOf("1") + 1;
		}
		System.out.println(i);*/

		String charset = System.getProperty("file.encoding");
		System.out.println(System.getProperty("java.net.dirs"));
		System.out.println(System.getProperty("java.class.path"));
		test t = new test();
		System.out.println(t.getClass().getClassLoader().getClass());
		System.out.println(charset);
        /*int num = 0;  
        for(int i = 0; i < 100; i++){  
            num = num++;  
        }  
        Integer i = 1 + 1;
        System.out.println("num="+i);*/  
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date1 = format.parse("2016-09-12 00:00");
			Date date2 = format.parse("2016-09-18 00:00");
			System.out.println(new Date().getTime());
			System.out.println(date1.getTime());
			System.out.println(date2.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
	}
}
