/**
 * 
 */
package day30;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author zhangchao
 * @date   2018年6月24日
 * 
 */
public class Sender {

	public static void main(String[] args) throws Exception{
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"bhz",
				"bhz",
				"tcp://localhost:61616");
		
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
//		Session session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE); 
//		Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE); 
		Session session = connection.createSession(Boolean.FALSE,Session.CLIENT_ACKNOWLEDGE); 
		
		Destination destination = session.createQueue("queue1"); 
		
//		MessageProducer messageProducer = session.createProducer(destination);
		MessageProducer messageProducer = session.createProducer(null);
		
//		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		for(int i=0;i<5;i++){
			TextMessage textMessage = session.createTextMessage("我是消息内容!!!id为："+i);
			//第一个参数：目的地
			//第二个参数：消息
			//第三个参数：是否持久化
			//第四个参数：优先级（0-9  0-4表示普通 5-9表示加急 默认为4）
			//第五个参数：消息在mq上的存放有效期
			messageProducer.send(destination,textMessage,DeliveryMode.NON_PERSISTENT,i,1000*60*2);
			System.out.println("生产者生产消息："+textMessage.getText());
		}
		
//		session.commit();
		
		if(connection!=null){
			connection.close();
		}
	}
}
