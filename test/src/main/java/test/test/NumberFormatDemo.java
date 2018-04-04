package test.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

import com.fc.bean.User;

/**
 * @author fangcong on 2018/1/11.
 */
public class NumberFormatDemo {

    private static ArrayBlockingQueue<User> users = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<>(4);
        map.put("key1", 1);
        System.out.println(map.size());
        /*double val = Double.parseDouble("339.81");
        long total = 5000;
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMinimumFractionDigits(1);
        String rate = format.format(val/total);
        System.out.println(rate);

        int a = 5 / 4;
        System.out.println(a);*/
        /*for (int i = 1; i <= 15; i++) {
            User user = new User();
            user.setRealName("zhang" + i);
            user.setAge(20 + i);
            ArrayBlockingQueue<User> queue = putOrGetQueen(user);
            printQueue(queue);
        }*/
        /*List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User("zhang" + i, 'M', 20 + i);
            users.add(user);
        }
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if (!"zhang2".equals(user.getRealName())) {
                user.setRealName(user.getRealName().concat("str"));
            } else {
                it.remove();
            }
        }
        for (User user : users) {
            System.out.println(user.toString());
        }*/
    }

    public static ArrayBlockingQueue<User> putOrGetQueen(User user) {
        if (users.size() == 10 && null != users.poll()) {
            users.offer(user);
        }
        users.offer(user);
        return users;
    }

    public static void printQueue(ArrayBlockingQueue<User> queue) {
        System.out.println(queue.size());
        for (User user : queue) {
            System.out.println(user.toString());
        }
        System.out.println("==================================");
    }
}
