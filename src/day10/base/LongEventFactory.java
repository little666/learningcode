package day10.base;

import com.lmax.disruptor.EventFactory;

public class LongEventFactory implements EventFactory{

	public Object newInstance(){
		return new LongEvent();
	}
	
}
