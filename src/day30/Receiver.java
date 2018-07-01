/**
 * 
 */
package day30;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author zhangchao
 * @date   2018年6月24日
 * 
 */
public class Receiver {

	public static void main(String[] args) throws Exception{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"bhz",
				"bhz",
				"tcp://localhost:61616");
		
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(Boolean.FALSE,Session.CLIENT_ACKNOWLEDGE); 
		
		Destination destination = session.createQueue("queue1"); 
		
		MessageConsumer messageConsumer = session.createConsumer(destination);
		
		while(true){
			TextMessage text = (TextMessage) messageConsumer.receive();
			text.acknowledge();
			if(text==null)break;
			System.out.println("收到内容："+text.getText());
		}
		
		if(connection!=null){
			connection.close();
		}
	}
}
