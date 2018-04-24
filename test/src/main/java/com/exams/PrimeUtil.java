package com.exams;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fangcong on 2018/4/11.
 */
public class PrimeUtil {

    public static boolean isPrime(int num) {
        int tmp = num;
        if (tmp < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(tmp); i++) {
            if (tmp % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * parallel用于将流进行并行化，提升执行效率
     *
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long primeCount = IntStream.range(1, 1000000).parallel().filter(PrimeUtil::isPrime).count();
        System.out.println(primeCount);
        System.out.println(System.currentTimeMillis() - start);

        List<Student> students = Arrays.asList(
            new Student("stu1", 99),
            new Student("stu2", 88),
            new Student("stu3", 66),
            new Student("stu4", 77)
        );
        long start1 = System.currentTimeMillis();
        double aveScore = students.parallelStream().mapToInt(s -> s.getSocre()).average().getAsDouble();
        System.out.println(aveScore);
        System.out.println(System.currentTimeMillis() - start1);

        long start2 = System.currentTimeMillis();
        int[] arr = new int[1000000];
        Random r = new Random();
        Arrays.parallelSetAll(arr, (i) -> r.nextInt());
        Arrays.parallelSort(arr);
        System.out.println(System.currentTimeMillis() - start2);
    }
}

@Data
@AllArgsConstructor
class Student {

    private String name;

    private Integer socre;
}