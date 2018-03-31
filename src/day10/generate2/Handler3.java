package day10.generate2;

import com.lmax.disruptor.EventHandler;

import day10.generate1.Trade;

public class Handler3 implements EventHandler<Trade>{

	@Override
	public void onEvent(Trade arg0, long arg1, boolean arg2) throws Exception {
		System.out.println("handler3: name: "+arg0.getName()+",price: "+arg0.getPrice()+"; instance: "+arg0.toString());
	}

}
