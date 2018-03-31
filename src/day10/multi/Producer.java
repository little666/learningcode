package day10.multi;

import com.lmax.disruptor.RingBuffer;

public class Producer {

	private final RingBuffer<Order> ringBuffer;

	public Producer(RingBuffer<Order> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(String data){
		long seq = ringBuffer.next();
		try{
			
		}catch(Exception e){
			
		}
	}
}
