package com.demo.producerConsumer;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;

import com.fc.common.ThreadPoolDemo;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.Getter;
import lombok.Setter;

/**
 * disruptor实现的生产者消费者模型
 *
 * @author fangcong on 2018/4/4.
 */
public class DisruptorPCDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = ThreadPoolDemo.getThreadFactoryPool();
        PCDataFactory factory = new PCDataFactory();
        int bufferSize = 1024;

        Disruptor<PCData> disruptor = new Disruptor<>(factory, bufferSize, service,
            ProducerType.MULTI,
            new BlockingWaitStrategy());

        disruptor.handleEventsWithWorkerPool(new Consumer(), new Consumer(), new Consumer(), new Consumer());
        disruptor.start();

        RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);

        for (long m = 0; m < 20; m++) {
            byteBuffer.putLong(0, m);
            producer.pushData(byteBuffer);
            Thread.sleep(100);
            System.out.println("push Data:" + m);
        }
    }
}

@Setter
@Getter
class PCData {
    private Long value;
}

/**
 * 消费者
 */
class Consumer implements WorkHandler<PCData> {

    @Override
    public void onEvent(PCData pcData) throws Exception {
        System.out.println(Thread.currentThread().getId()
            + ":Event:--" + pcData.getValue() * pcData.getValue() + "--");
    }
}

/**
 * PCData生产工厂
 */
class PCDataFactory implements EventFactory<PCData> {

    @Override
    public PCData newInstance() {
        return new PCData();
    }
}

/**
 * 生产者
 */
class Producer {

    private final RingBuffer<PCData> buffer;

    public Producer(RingBuffer<PCData> buffer) {
        this.buffer = buffer;
    }

    public void pushData(ByteBuffer byteBuffer) {
        long sequence = buffer.next();
        PCData data = buffer.get(sequence);
        data.setValue(byteBuffer.getLong(0));

        buffer.publish(sequence);
    }
}