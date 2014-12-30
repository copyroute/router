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
	

	
}
