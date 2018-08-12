package day31;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * @author zhangchao
 * @date   2018年7月14日
 * 
 */
public class Producer {

	public static void main(String[] args) throws MQClientException, InterruptedException {
		DefaultMQProducer producer = new DefaultMQProducer("message_producer");
		producer.setNamesrvAddr("192.168.43.121:9876;192.168.43.122:9876;192.168.43.123:9876;192.168.43.124:9876");
		producer.start();
		
		for(int i=0;i<100;i++){
			try{
				Message msg = new Message("Topic1",
						"Tag1",
						("Hello RocketMQ "+i).getBytes());
				SendResult sendResult = producer.send(msg);
				System.out.println(sendResult );
			}catch(Exception e){
				e.printStackTrace();
				Thread.sleep(1000);
			}
			
		}
		
		producer.shutdown();
	}

}
