package com.copyroute.webservices.amqp;


import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import java.nio.charset.Charset;

public class Json_MessageConverter extends JsonMessageConverter {


    public static void Log(String message) { System.out.println(message); }

    private static final String ENCODING = Charset.defaultCharset().name();
   
    @Override
     protected Message createMessage(Object object, MessageProperties messageProperties) {
 		Log("================= >>>>>> CreateMessage: Search Message Received ");	    
        Message message = new Message( object.toString().getBytes(), new MessageProperties());
        Log("CreateMessage: " + message.getBody().toString());
        return message;
     }

     @Override
     public Object fromMessage(Message message) throws MessageConversionException {
 		Log("================= >>>>>> FromMessage: Search Message Received ");	    
 		Log("================= >>>>>> : " + message.getBody());	    
//	         message.getMessageProperties().setHeader("content-type", "text/plain");
//	         try {
//	             Log("FromMessage: " + message.toString());
//	             Log("Body: " + new String(message.getBody(), ENCODING));
//	         }
//	         catch (Exception e) {}
         return message;
     }
	
}
