package day10.generate2;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import day10.generate1.Trade;

public class TradePublisher implements Runnable{

	private CountDownLatch latch;
	
	Disruptor<Trade> disruptor;

	private static int LOOP = 100;
	
	public TradePublisher(CountDownLatch latch, Disruptor<Trade> disruptor) {
		this.latch = latch;
		this.disruptor = disruptor;
	}

	@Override
	public void run() {
		TradeEventTranslator tradeEventTranslator = new TradeEventTranslator();
		disruptor.publishEvent(tradeEventTranslator);
		latch.countDown();
	}

}

class TradeEventTranslator implements EventTranslator<Trade>{

	private Random random = new Random();
	
	@Override
	public void translateTo(Trade arg0, long arg1) {
		this.generateTrade(arg0);
	}
	
	private Trade generateTrade(Trade trade){
		trade.setPrice(random.nextDouble()*9999);
		return trade;
	}
}