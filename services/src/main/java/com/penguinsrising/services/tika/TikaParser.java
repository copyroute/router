//package com.penguinsrising.services.tika;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.ByteArrayInputStream;
//import java.nio.charset.StandardCharsets;
//
//import javax.annotation.PostConstruct;
//import javax.inject.Singleton;
//
//import org.apache.tika.exception.TikaException;
//import org.apache.tika.metadata.Metadata;
//import org.apache.tika.parser.AutoDetectParser;
//import org.apache.tika.parser.ParseContext;
//import org.apache.tika.parser.Parser;
//import org.apache.tika.parser.html.HtmlParser;
//import org.apache.tika.sax.BodyContentHandler;
//import org.springframework.stereotype.Component;
//import org.xml.sax.ContentHandler;
//import org.xml.sax.SAXException;
//
//import com.penguinsrising.global.Statics;
//
//
//@Component
//@Singleton
//public class TikaParser {
//
//	@PostConstruct
//	public void init(){ Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString()); }
//
//	public static void parseFile(String filename)
//	{
//		InputStream is = null;
//        try {
//	        is = new BufferedInputStream(new FileInputStream(new File(filename)));
//	        parse(is);
//        }
//        catch (IOException e) {e.printStackTrace();} 
//        finally {
//            if (is != null) {
//                try {is.close();} 
//                catch(IOException e) {e.printStackTrace();}
//            }
//        }
//	}
//
//	public static void parseText(String text){
//		
//		Statics.Log("Parsing Text" ); 
//		try {
//			InputStream stream = 
//					new ByteArrayInputStream(
//							text.getBytes(StandardCharsets.UTF_8));
//			
//			ContentHandler handler = new BodyContentHandler();
//	        Metadata metadata = new Metadata();
//	        
//			new HtmlParser().parse(stream, handler, metadata, new ParseContext());
//	        String plainText = handler.toString();
//			Statics.Log("Plain Text : " + plainText ); 
//	        
//		} 
//		catch (IOException e) {e.printStackTrace();} 
//		catch (SAXException e) {e.printStackTrace();} 
//		catch (TikaException e) {e.printStackTrace();}
//	}
//	
//	public static void parse(InputStream is){
//		Statics.Log("Parsing Text" ); 
//		 
//        try {
//            Parser parser = new AutoDetectParser();
//            ContentHandler handler = new BodyContentHandler(System.out);
//            Metadata metadata = new Metadata();
//
//            parser.parse(is, handler, metadata, new ParseContext()); 
//            for (String name : metadata.names()) {
//                String value = metadata.get(name);
// 
//                if (value != null) {
//                    System.out.println("Metadata Name:  " + name);
//                    System.out.println("Metadata Value: " + value);
//                }
//            }
//        } 
//        catch (TikaException e) {e.printStackTrace();} 
//        catch (SAXException e) {e.printStackTrace();} 
//        catch (IOException e) {e.printStackTrace();} 
//	}
//}
