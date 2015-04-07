package com.copyroute.services.amqp;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copyroute.cdm.rss.*;
import com.copyroute.cdm.global.Statics;
import com.copyroute.cdm.util.json.PojoMapper;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import  org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

//@Named
//@Singleton
@Service
public class Amqp_Service 
{
	public static void Log(String message) { Statics.Log(message); }
	
	protected static final String ENCODING = Charset.defaultCharset().name();
	
//	@Autowired private Configuration cfg;
	@Autowired private FreeMarkerConfigurer cfgSpring;
	
	@Autowired  private RabbitTemplate rabbitTemplate;
	public String exchange = "penguins.exchange";
	public String routing = "";

	public void handleMessage(Object obj){
		Log("\n\n ============= !!! Amqp Object MessageHandler !!!=========== ");
		Log("\n\n ============= !!! Amqp Object MessageHandler !!!=========== " + obj.toString());
	}
	public void handleMessage(String text){
		Log("\n\n ============= !!! Amqp String MessageHandler!!!=========== " );
		Log("\n\n ============= !!! Amqp String MessageHandler!!!=========== " + text);
	}
	public void handleMessage(Message message){
		Log("\n\n ============= !!! Amqp Message MessageHandler!!!=========== ");
		Log("\n\n ============= !!! Amqp Message MessageHandler!!!=========== " + message.toString());
	}
	
	
    @PostConstruct
    public void init(){ 
    	Log("================= >>>>>> Initialized: " + this.getClass().toString());
//    	cfg.setClassForTemplateLoading(this.getClass(), "/");
//        cfg.setDefaultEncoding("UTF-8");
//        cfg.setLocale(Locale.US);
//        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

	// Init ======================================================
	public void initAmqp(String exchangeName){
		this.exchange = exchangeName;
		this.rabbitTemplate.setExchange(this.exchange);
		this.rabbitTemplate.setReplyTimeout(15000);	// Negative = infinite wait
	}
	
	public void sendAMQPEvent(String message){
		Log("AMQP Publishing : ");
		rabbitTemplate.setReplyTimeout(15000);	// Negative = infinite wait
		Object retVal = rabbitTemplate.convertSendAndReceive(
			exchange,
			routing,
			message
		);
		Log("Published Over Stomp w/Sock(j)s: " + retVal  );			
	}	
	
	// AMQP Publisher
	// Call the Remote Service via AMQP
	// We use the intercepted packageName, className, and methodName as the AMQP Routing-Key
	// The Routing-Key is de-encoded by the Message-Consumer to call the actual service. 
	public void sendAMQPEvent(RssItem rssItem){
		Log("AMQP Publishing : ");
		Object retVal = rabbitTemplate.convertSendAndReceive(
			this.exchange,
			this.routing,
			rssItem
		);
		Log("Published : " + retVal  );			
	}

	


	public void sendFTLMessage(String exchange, String routingKey, Map<String, Object> input){
		String message = "";
		try {
			message = PojoMapper.toJson(input, true);
		} 
		catch (JsonMappingException e) {Statics.Log(e.getMessage());} 
		catch (JsonGenerationException e) {Statics.Log(e.getMessage());} 
		catch (IOException e) {Statics.Log(e.getMessage());}
		rabbitTemplate.setExchange(exchange);
		rabbitTemplate.setReplyTimeout(3000);	// Negative = infinite wait
		rabbitTemplate.convertAndSend(exchange,routingKey, message);
		Log("Sent Message: " + exchange + ":" + routingKey); 
	}
	public String convertFreemarkerTemplate(String templateLocation, Object message){
		String output = "";
		try {
			// Get FreeMarker Template (“banner.ftl”)
			Template template = cfgSpring.getConfiguration().getTemplate(templateLocation);
			try {
				Writer writer = new StringWriter();
				template.process(message, writer);
			    output = writer.toString();
			} 
			catch (TemplateException e) { Statics.Log( e.getMessage() ); }
		} 
	    catch (IOException e) {Statics.Log(e.getMessage());}
		return output;	    
	}

	
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
		Object retVal = 
			rabbitTemplate.convertSendAndReceive(
					exchange,routingKey,message);
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