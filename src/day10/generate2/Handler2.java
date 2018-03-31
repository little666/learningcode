package day10.generate2;

import com.lmax.disruptor.EventHandler;

import day10.generate1.Trade;

public class Handler2 implements EventHandler<Trade>{

	@Override
	public void onEvent(Trade arg0, long arg1, boolean arg2) throws Exception {
		System.out.println("handler2:set price");
		arg0.setPrice(17.0);
//		Thread.sleep(1000);
	}

}
