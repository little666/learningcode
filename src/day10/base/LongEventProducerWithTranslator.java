package day10.base;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class LongEventProducerWithTranslator {

	private static final EventTranslatorOneArg<LongEvent,ByteBuffer> TRANSLATOR = 
			new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
				@Override
				public void translateTo(LongEvent event, long sequence, ByteBuffer buffer) {
					event.setValue(buffer.getLong(0));
				}
			};
	
	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(ByteBuffer byteBuffer) {
		ringBuffer.publishEvent(TRANSLATOR,byteBuffer);
	}
}
