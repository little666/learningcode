package day31;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @author zhangchao
 * @date   2018年7月14日
 * 
 */
public class Consumer {

	public static void main(String[] args) throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("message_consumer");
		consumer.setNamesrvAddr("192.168.43.121:9876;192.168.43.122:9876;192.168.43.123:9876;192.168.43.124:9876");
		
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		
		consumer.subscribe("Topic1", "Tag1|| Tag2 || Tag3");
//		consumer.setMessageModel(MessageModel.BROADCASTING);
//		consumer.setConsumeMessageBatchMaxSize(10);
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//				System.out.println(Thread.currentThread().getName()+"Receive New Message:"+msgs);
				System.out.println("消息条数："+msgs.size());
				MessageExt msg = msgs.get(0);
				try {
					String topic = msg.getTopic();
					String msgBody = new String(msg.getBody(),"utf-8");
					String tags = msg.getTags();
					System.out.println("收到消息：|topic:"+topic+" ,tags:"+tags+" ,msg:"+msgBody);
				
//					System.out.println("======开始暂停======");
//					Thread.sleep(60000);
//					if("Hello RocketMQ 4".equals(msgBody)){
//						System.out.println("======失败消息开始=======");
//						System.out.println(msg);
//						System.out.println(msgBody);
//						System.out.println("======失败消息结束=======");
//						int a=1/0;
//					}
				} catch (Exception e) {
					e.printStackTrace();
//					if(msg.getReconsumeTimes()==2){
//						System.out.println("重试2两次后仍然失败的消息。。");
//						return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//					}
					return ConsumeConcurrentlyStatus.RECONSUME_LATER;
				}
				
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		
		consumer.start();
		
		System.out.println("Consumer Started.");
	}

}
