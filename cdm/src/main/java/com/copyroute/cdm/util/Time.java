package com.copyroute.cdm.util;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.copyroute.cdm.global.Statics;

public class Time {

	// Get XMLGregorianCalendar Now
    public static XMLGregorianCalendar getXMLGregorianCalendarNow() {
		try{
		    GregorianCalendar gregorianCalendar = new GregorianCalendar();
		    DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
		    XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
	        return now;
		}catch(DatatypeConfigurationException ex){Statics.Log("Calender Exception: " + ex.getMessage() );}
		return null;
    }
    
    // Convert XMLGregorianCalendar
	public static XMLGregorianCalendar convertXMLGregorianCalendar(Date date0){
		try{
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(date0);
			XMLGregorianCalendar date1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			return date1;
		}catch(DatatypeConfigurationException ex){ Statics.Log("Calender Exception: " + ex.getMessage() );}
		return null;
	}

}
