package mqtest;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.msg.enums.MessageType;
import com.msg.enums.SendChannel;
import com.msg.event.SendMessageEvent;
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
        
        SendMessageEvent event = new SendMessageEvent();
        event.setSendId(1L);
        event.setSenderName("admin");
        event.setTitle("系统消息");
        event.setContent("你有100元红包到账");
        event.setChanel(SendChannel.BAIDU_PUSH);
        event.setType(MessageType.PRIVATE);
        event.setRecId(1L);
        
        channel.basicPublish(EXCHANGE_NAME, "message.internal", null, JSON.toJSON(event).toString().getBytes());
  
        channel.close();
        connection.close();
        System.out.println(JSON.toJSON(event));
    }
    
}
