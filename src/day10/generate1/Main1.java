package day10.generate1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;

public class Main1 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int BUFFER_SIZE=1024;
		int THREAD_NUMBERS=4;
		
		final RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {

			@Override
			public Trade newInstance() {
				return new Trade();
			}
		}, BUFFER_SIZE,new YieldingWaitStrategy());
		
		ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);
		
		SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
		
		BatchEventProcessor<Trade> transProcessor = new BatchEventProcessor<Trade>(ringBuffer, sequenceBarrier, new TradeHandler());
		
		ringBuffer.addGatingSequences(transProcessor.getSequence());
		
		executors.submit(transProcessor);
		
		Future future = executors.submit(new Callable<Void>(){

			@Override
			public Void call() throws Exception {
				long seq;
				for(int i=0;i<10;i++){
					seq=ringBuffer.next();
					ringBuffer.get(seq).setPrice(Math.random()*9999);
					ringBuffer.publish(seq);
				}
				return null;
			}
			
		});
		
		future.get();
		Thread.sleep(1000);
		transProcessor.halt();
		executors.shutdown();
	}
}
