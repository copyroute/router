package com.copyroute.services.amqp;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.copyroute.cdm.rss.*;
import com.copyroute.services.global.Statics;

//@Named
//@Singleton
@Service
public class Amqp_Service 
{
	public static void Log(String message) { Statics.Log(message); }
	
	protected static final String ENCODING = Charset.defaultCharset().name();
	
	@Autowired  private RabbitTemplate rabbitTemplate;
	public String exchange = "penguins.exchange";
	public String routing = "";

	// 2 Log calls-- in case Message breaks || null
	public void handleMessage(Object obj){
		Log("\n\n ============= !!! Amqp Object MessageHandler !!!=========== ");
		Log("\n\n " + obj.toString());
	}
	public void handleMessage(String text){
		Log("\n\n ============= !!! Amqp String MessageHandler!!!=========== " );
		Log("\n\n " + text);
	}
	public void handleMessage(Message message){
		Log("\n\n ============= !!! Amqp Message MessageHandler!!!=========== ");
		Log("\n\n " + message.toString());
	}
	
	
    @PostConstruct
    public void init(){ 
    	Log("================= >>>>>> Initialized: " + this.getClass().toString());
    }

	// Init ======================================================
	public void initAmqp(String exchangeName){
		this.exchange = exchangeName;
		this.rabbitTemplate.setExchange(this.exchange);
		this.rabbitTemplate.setReplyTimeout(15000);	// Negative = infinite wait
	}
		
	// AMQP Publisher
	// Call the Remote Service via AMQP
	// We use the intercepted packageName, className, and methodName as the AMQP Routing-Key
	// The Routing-Key is de-encoded by the Message-Consumer to call the actual service. 
	public void sendMessage(String exchange, String routingKey, String message){
		rabbitTemplate.setExchange(exchange);
		rabbitTemplate.setReplyTimeout(3000);	// Negative = infinite wait
		rabbitTemplate.convertAndSend(exchange,routingKey,message);
		Log("Sent Message: " + exchange + ":" + routingKey + ":" + message); 
	}	
	public Object sendAndReceiveMessage(String exchange, String routingKey, String message){
		Log("Sending and Receiving Message : " + exchange);
		rabbitTemplate.setExchange(exchange);
		rabbitTemplate.setReplyTimeout(3000);	// Negative = infinite wait
		Object retVal = rabbitTemplate.convertSendAndReceive(exchange,routingKey,message);
		Log("Sent and Received Message: " + exchange + ":" + routingKey + ":" + retVal.toString() ); 
		return retVal;
	}
	
	public void sendDefault(String routingKey, String message){
		rabbitTemplate.setExchange("");
		rabbitTemplate.setEncoding("text/plain");
		rabbitTemplate.convertAndSend(routingKey, message);
		Log("Sent To Default: " + exchange + routingKey + ":" + routingKey); 
	}
	
	public Message getMessage(String queueName){
		// Message message = this.rabbitTemplate.receive();
		Message message = rabbitTemplate.receive(queueName);
		Log("Received Message : " + queueName ); 
		return message;
	}
	
}