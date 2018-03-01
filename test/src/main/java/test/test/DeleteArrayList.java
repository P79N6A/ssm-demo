package test.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 删除arrayList中的某个元素
 *
 * @author fangcong
 */
public class DeleteArrayList {

    /**
     * arrayList中指定删除的元素不连续存储时可以完全删除<br>
     * 指定删除的元素存在连续存储时不能实现完全删除
     *
     * @param list
     */
    public static void deleteElement1(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if ("ab".equals(list.get(i))) {
                list.remove(i);
            }
        }
    }

    /**
     * 可以完全删除
     *
     * @param list
     */
    public static void deleteElement2(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if ("ab".equals(list.get(i))) {
                list.remove(i);
                --i;
            }
        }
    }

    /**
     * 可以完全删除
     *
     * @param list
     */
    public static void deleteElement3(ArrayList<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String str = it.next();
            if ("ab".equals(str)) {
                it.remove();
            }
        }
    }

    private static void printList(ArrayList<String> list) {
        System.out.println(list.size());
        for (String str : list) {
            System.out.print(str + "\t");
        }
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("aa");
        list.add("ab");
        list.add("bb");
        list.add("cc");
        list.add("ab");
        list.add("dd");
        list.add("ab");
        list.add("ab");
        list.add("ee");
        List subList = list.subList(0, 5);
        List<String> list1 = new ArrayList<>(subList);
        Serializable serializable = (Serializable)list1;
        System.out.println(list1.size());

        /**deleteElement1(list);
         deleteElement2(list);
         deleteElement3(list);
         printList(list);*/
        Map<String, Object> map = new HashMap<>();
        long sellerId = 117255663;
        int level = 5;
        map.put("level", 5);
        map.put("sellerId", sellerId);
        long val = (Long)map.get("sellerId");
        int val2 = (Integer)map.get("level");
        System.out.println(9 / 4);
        System.out.println(val2);
    }
}
