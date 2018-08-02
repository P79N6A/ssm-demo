package com.demo.producer2consumer;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;

import com.fc.common.ThreadPoolUtils;
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
public class DisruptorPcDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = ThreadPoolUtils.getThreadFactoryPool(3);
        PcDataFactory factory = new PcDataFactory();
        int bufferSize = 1024;

        //corePoolSize数量消费者消费
        Disruptor<PcData> disruptor = new Disruptor<>(factory, bufferSize, service,
            ProducerType.MULTI,
            new BlockingWaitStrategy());

        disruptor.handleEventsWithWorkerPool(
            new Consumer(),
            new Consumer(),
            new Consumer(),
            new Consumer(),
            new Consumer());
        disruptor.start();

        RingBuffer<PcData> ringBuffer = disruptor.getRingBuffer();
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
class PcData {
    private Long value;
}

/**
 * 消费者
 */
class Consumer implements WorkHandler<PcData> {

    @Override
    public void onEvent(PcData pcData) throws Exception {
        System.out.println(Thread.currentThread().getId()
            + ":Event:--" + pcData.getValue() * pcData.getValue() + "--");
    }
}

/**
 * PCData生产工厂
 */
class PcDataFactory implements EventFactory<PcData> {

    @Override
    public PcData newInstance() {
        return new PcData();
    }
}

/**
 * 生产者
 */
class Producer {

    private final RingBuffer<PcData> buffer;

    public Producer(RingBuffer<PcData> buffer) {
        this.buffer = buffer;
    }

    public void pushData(ByteBuffer byteBuffer) {
        long sequence = buffer.next();
        try {
            PcData data = buffer.get(sequence);
            data.setValue(byteBuffer.getLong(0));
        } finally {
            buffer.publish(sequence);
        }
    }
}