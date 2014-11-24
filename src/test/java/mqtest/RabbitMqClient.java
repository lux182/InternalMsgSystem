package mqtest;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

public class RabbitMqClient {

    private static final String QUEUE_NAME = "internalMsgQ";
    private static final String EXCHANGE_NAME = "msgExchange";

    public static void main(String args[]) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
    	ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
//        factory.setVirtualHost("admin_vhost");
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        
        Channel channel = connection.createChannel();

        byte[] messageBodyBytes = "hello world".getBytes();  
        
        
        channel.basicPublish(EXCHANGE_NAME, "message.internal", null, messageBodyBytes);
  
        channel.close();
        connection.close();
    }
    
}
