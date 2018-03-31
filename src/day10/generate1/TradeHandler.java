package day10.generate1;

import java.util.UUID;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class TradeHandler implements EventHandler<Trade>,WorkHandler<Trade>{

	@Override
	public void onEvent(Trade arg0, long arg1, boolean arg2) throws Exception {
		this.onEvent(arg0);
	}

	@Override
	public void onEvent(Trade arg0) throws Exception {
		arg0.setId(UUID.randomUUID().toString());
		System.out.println(arg0.getId());
	}

}
