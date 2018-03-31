package day10.generate2;

import com.lmax.disruptor.EventHandler;

import day10.generate1.Trade;

public class Handler5 implements EventHandler<Trade>{

	@Override
	public void onEvent(Trade arg0, long arg1, boolean arg2) throws Exception {
		System.out.println("handler5: get price: "+arg0.getPrice());
		arg0.setPrice(arg0.getPrice()+3.0);
	}

}
