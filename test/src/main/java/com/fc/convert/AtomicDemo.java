package com.fc.convert;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author fangcong on 2018/9/18.
 */
public class AtomicDemo {

    private static AtomicInteger intAtomic = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        /*ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        IntStream.range(0, 1000).forEach(i -> {
            Runnable task = () -> intAtomic.updateAndGet(operand -> operand + 2);
            service.submit(task);
        });

        *//*Thread.sleep(1000); // 等待执行完毕*//*
        // 关闭之后判断所有任务是否执行完成
        service.shutdown();
        while (true) {
            if (service.isTerminated()) {
                break;
            }
        }
        System.out.println(intAtomic.get());*/

        String str = String.join(":", "foobar", "foo", "bar");
        System.out.println(str);

        String str1 = str.chars().distinct().mapToObj(c -> String.valueOf((char)c)).sorted()
            .collect(Collectors.joining(";"));
        System.out.println(str1);
        Stream.of(str.split(":")).filter(s -> s.contains("foo")).collect(Collectors.toList());
    }

    /**
     * Optional来进行null检查
     *
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> Optional<T> resolve(Supplier<T> supplier) {
        try {
            T result = supplier.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }
}
