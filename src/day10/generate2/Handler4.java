package day10.generate2;

import com.lmax.disruptor.EventHandler;

import day10.generate1.Trade;

public class Handler4 implements EventHandler<Trade>{

	@Override
	public void onEvent(Trade arg0, long arg1, boolean arg2) throws Exception {
		System.out.println("handler4: get name: "+arg0.getName());
		arg0.setName(arg0.getName()+"h4");
	}

}
