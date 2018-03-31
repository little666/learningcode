package day10.base;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class LongEventMain {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		LongEventFactory factory = new LongEventFactory();
	
		int ringBufferSize = 1024*1024;
		
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory,ringBufferSize,executor,ProducerType.SINGLE,new YieldingWaitStrategy());
	
		disruptor.handleEventsWith(new LongEventHandler());
		
		disruptor.start();
		
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
	
//		LongEventProducer producer = new LongEventProducer(ringBuffer);
		LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
	
		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		
		for(long a=0;a<100;a++){
			byteBuffer.putLong(0,a);
			producer.onData(byteBuffer);
		}
		
		disruptor.shutdown();
		executor.shutdown();
	}
}
