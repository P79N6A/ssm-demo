package com.exams;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

import com.fc.bean.User;
import org.apache.commons.lang.time.DateUtils;

/**
 * @author fangcong on 2018/4/10.
 */
public class NormalTest {

    private static final Student STUDENT = new Student("ez", 60);

    private static final int MAX_THREAD = 3;

    private static final int TASK_COUNT = 2;

    private static final int TASK_TARGET = 10000000;

    /**
     * 无锁的原子操作
     */
    private AtomicLong acount = new AtomicLong(0);

    /**
     * 更快的原子类
     */
    private LongAdder lacount = new LongAdder();

    /**
     * 非原子操作
     */
    private long count = 0;

    static CountDownLatch cdlSync = new CountDownLatch(TASK_COUNT);

    static CountDownLatch cdlAtomic = new CountDownLatch(TASK_COUNT);

    static CountDownLatch cdlAdder = new CountDownLatch(TASK_COUNT);

    protected synchronized long incr() {
        return ++count;
    }

    protected synchronized long getCount() {
        return count;
    }

    public class SyncThread implements Runnable {

        private String name;

        private long startTime;

        NormalTest normalTest;

        public SyncThread(long startTime, NormalTest normalTest) {
            this.startTime = startTime;
            this.normalTest = normalTest;
        }

        @Override
        public void run() {
            long v = normalTest.getCount();
            while (v < TASK_TARGET) {
                v = normalTest.incr();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("long cost:" + (endTime - startTime) + "ms v:" + v);
            cdlSync.countDown();
        }
    }

    public class AtomicThread implements Runnable {

        protected String name;

        protected long startTime;

        public AtomicThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = acount.get();
            while (v < TASK_TARGET) {
                v = acount.incrementAndGet();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("AtomicLong cost:" + (endTime - startTime) + "ms v:" + v);
            cdlAtomic.countDown();
        }
    }

    public class AdderThread implements Runnable {

        protected long startTime;

        public AdderThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = lacount.sum();
            while (v < TASK_TARGET) {
                lacount.increment();
                v = lacount.sum();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("AdderLong cost:" + (endTime - startTime) + "ms v:" + v);
            cdlAdder.countDown();
        }
    }

    public void testSync() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(MAX_THREAD);
        long startTime = System.currentTimeMillis();
        SyncThread syncThread = new SyncThread(startTime, this);
        for (int i = 0; i < TASK_COUNT; i++) {
            service.submit(syncThread);
        }
        cdlSync.wait();
        service.shutdown();
    }

    public void testAtomic() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(MAX_THREAD);
        long startTime = System.currentTimeMillis();
        AtomicThread atomicThread = new AtomicThread(startTime);
        for (int i = 0; i < MAX_THREAD; i++) {
            service.submit(atomicThread);
        }
        cdlAtomic.wait();
        service.shutdown();
    }

    public void testAdder() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(MAX_THREAD);
        long startTime = System.currentTimeMillis();
        AdderThread adderThread = new AdderThread(startTime);
        for (int i = 0; i < MAX_THREAD; i++) {
            service.submit(adderThread);
        }
        cdlAdder.wait();
        service.shutdown();
    }

    public static void main(String[] args) throws Exception {
        Date date = com.fc.common.DateUtils.getFormatDate("2018-08-31 14:09:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println(date.getTime() + 3600000);
        Date date1 = new Date();
        Date date2 = new Date(date1.getTime() + 3600000);
        System.out.println(com.fc.common.DateUtils.getFormatDateTime(date1));
        System.out.println(com.fc.common.DateUtils.getFormatDateTime(date2));

        /*long start = System.nanoTime();*/
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User("name" + i, i % 2 == 0 ? 'M' : 'F', 20 + i);
            list.add(user);
        }
        list.forEach(user -> user.setLoginName(user.getRealName()));
        for (User user : list) {
            System.out.println(user);
        }
        /*List<User> subList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            subList.add(list.get(i));
        }*/
        //System.out.println("cost:" + (System.nanoTime() - start));

        /*final int num = 2;
        Function<Integer, Integer> function = (from) -> from * num;
        System.out.println(function.apply(3));

        //NormalTest normalTest = new NormalTest();
        //normalTest.testSync();
        //normalTest.testAtomic();
        //normalTest.testAdder();

        /*int a = 132547698;
        char[] c = String.valueOf(a).toCharArray();
        System.out.println(niXuStr(c));*/

        int i = (int)(129600 - DateUtils.getFragmentInSeconds(Calendar.getInstance(), Calendar.DATE));
        System.out.println(i);

        //List<Double> nums = Arrays.asList(12d, 14d, 17d, 22d);
        //nums.stream().map((d) -> d.toString()).forEach(System.out::print);

        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2);

        //1、根据所在城市查店铺列表，无店铺直接返回
        /*List<ShopLocation> list = new ArrayList<>();
        ShopLocation location2 = new ShopLocation("hangzhou", "120.021957", "30.275341");
        ShopLocation location1 = new ShopLocation("hangzhou", "120.020776", "30.27467");
        ShopLocation location3 = new ShopLocation("hangzhou", "120.026208", "30.279212");
        ShopLocation location4 = new ShopLocation("hangzhou", "120.090935", "30.286972");
        list.add(location1);
        list.add(location2);
        list.add(location3);
        list.add(location4);
        //2、根据经纬度流式计算距离并排序并返回结果
        double destLongitude = 120.017113;
        double destLatitude = 30.279192;
        List<ShopLocation> result = new ArrayList<>();
        list.stream().sorted((a, b) -> {
            int distanceA = (int)getDistance(destLongitude, destLatitude,
                Double.parseDouble(a.getLongitude()), Double.parseDouble(a.getLatitude())
            );
            int distanceB = (int)getDistance(destLongitude, destLatitude,
                Double.parseDouble(b.getLongitude()), Double.parseDouble(b.getLatitude())
            );
            return distanceA < distanceB ? -1 : 1;
        }).filter(shopLocation -> {
            int distance = (int)getDistance(destLongitude, destLatitude,
                    Double.parseDouble(shopLocation.getLongitude()), Double.parseDouble(shopLocation.getLatitude()));
                return distance <= 2000;
            }
        ).forEach(result::add);
        result.forEach(System.out::println);

        list.forEach(shopLocation -> {
            int distance = (int)getDistance(destLongitude, destLatitude,
                Double.parseDouble(shopLocation.getLongitude()), Double.parseDouble(shopLocation.getLatitude()));
            System.out.println(shopLocation.toString() + " " + distance);
        });

        int distance = (int)getDistance(120.017113, 30.279192, 120.090935, 30.286972);
        System.out.println(distance);*/
    }

    private static String niXuStr(char[] c) {
        if (c.length == 0) {
            return "";
        } else {
            String s1 = String.valueOf(c[c.length - 1]);
            String c2 = String.copyValueOf(c, 0, c.length - 1);
            return s1 + niXuStr(c2.toCharArray());
        }
    }

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
            + Math.cos(radLat1) * Math.cos(radLat2)
            * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

}

class ShopLocation {

    /**
     * 城市
     */
    private String city;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 维度
     */
    private String latitude;

    /**
     * 店铺名
     */
    private String shopName;

    /**
     * 店铺地址
     */
    private String address;

    public ShopLocation(String city, String longitude, String latitude) {
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ShopLocation{" +
            "city='" + city + '\'' +
            ", longitude='" + longitude + '\'' +
            ", latitude='" + latitude + '\'' +
            '}';
    }
}


