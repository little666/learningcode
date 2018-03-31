package day10.generate2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import day10.generate1.Trade;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		long beginTime = System.currentTimeMillis();
		int bufferSize = 1024;
		ExecutorService executor = Executors.newFixedThreadPool(8);
		
		Disruptor<Trade> disruptor = new Disruptor<Trade>(new EventFactory<Trade>() {
			@Override
			public Trade newInstance() {
				return new Trade();
			}
		},bufferSize,executor,ProducerType.SINGLE,new BusySpinWaitStrategy());
		
//		EventHandlerGroup<Trade> handlerGroup = disruptor.handleEventsWith(new Handler1(),new Handler2());
//		
//		handlerGroup.then(new Handler3());
		
		Handler1 handler1 = new Handler1();
		Handler2 handler2 = new Handler2();
		Handler3 handler3 = new Handler3();
		Handler4 handler4 = new Handler4();
		Handler5 handler5 = new Handler5();
		
		disruptor.handleEventsWith(handler1,handler2);
		disruptor.after(handler1).handleEventsWith(handler4);
		disruptor.after(handler2).handleEventsWith(handler5);
		disruptor.after(handler4,handler5).handleEventsWith(handler3);
		
		disruptor.start();
		CountDownLatch latch = new CountDownLatch(1);
		
		executor.submit(new TradePublisher(latch,disruptor));
		
		latch.await();
		
		disruptor.shutdown();
		executor.shutdown();
		System.out.println("×ÜºÄÊ±£º"+(System.currentTimeMillis()-beginTime));
	}

}
