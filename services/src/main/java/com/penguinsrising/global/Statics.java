package com.penguinsrising.global;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Statics {

	
	// PlayList Behaviors
	public static boolean reloadPlayListDB = true;
	
	// Polling Behaviors
	public static boolean shouldRssUpdate = true;
	public static boolean saveMessagesEnabled = true;
	public static int pollerSleepDT = 3000;
	
	// Formatting
	public static boolean JsonPrettyPrint = true;

	// Loggging
	public static boolean loggingOn = false;

	// Default DataList
	public static String dataListName = "ABC";	// "myFeedList";
	
	// ================================================================
	
	private static final Logger logger = Logger.getLogger(Statics.class);
	public static void Log(String message){
		if(logger.isDebugEnabled()){
			logger.debug(message);
		}	
		if(loggingOn){
			System.out.println("System : " + message);
		}
			
	}
	
	public static String getDomain(HttpServletRequest request){
		String domain = "";
//		try {
			domain = request.getHeader("X-Forwarded-Host");

			// Set default for non-proxied instances
			if(domain == null || domain.isEmpty())
				domain = "penguins-rising.com";

			// DEV-HACK
			//domain = "theysed.com";

			Statics.Log("Determined Request URL : " + domain);
			
//		} 
//		catch (MalformedURLException e) {Statics.Log(e.getMessage());} 
		return domain;
	}
	public static String getTheme(HttpServletRequest request){
		String domain = getDomain(request);
		if(domain.contains("penguins-rising"))
			return "dark-hive";
		else if(domain.contains("copyroute"))
			return "cupertino";
		else if(domain.contains("theysed"))
			return "dot-luv";
		else
			return "black-tie";
	}

	
}
