package com.penguinsrising.services.nlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.penguinsrising.global.Statics;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;

@Component
@Singleton
public class OpenNLP {

	
	static SentenceDetectorME sdetector ;
	static Tokenizer tokenizer ;
	static TokenNameFinderModel model ;
	static PerformanceMonitor perfMon ;
	static POSTaggerME tagger ;
	static ChunkerME chunkerME ;
	
	// ======================================================================================
	// ======================================================================================
	@PostConstruct
	public void init(){ 
		perfMon = new PerformanceMonitor(System.err, "sent");
		perfMon.start();
		Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString()); 
	}
	
	@PreDestroy
	public void destroy(){
		perfMon.stopAndPrintFinalResult();
	}
	// ======================================================================================
//
//	// ======================================================================================
//	// By its name, name finder just finds names in the context. 
//	// Check out the following example to see what name finder can do. 
//	// It accepts an array of strings, and find the names inside.
//	// ======================================================================================
//	public static void findName(String[] sentence ) throws IOException 	{
//		find(sentence, "/nlp/en-ner-person.bin", "Name");
//	}
//	public static void findOrganization(String[] sentence ) throws IOException 	{
//		find(sentence, "/nlp/en-ner-organization.bin", "Organizations");
//	}
//	public static void findLocation(String[] sentence ) throws IOException 	{
//		find(sentence, "/nlp/en-ner-location.bin", "Locations");
//	}
//	public static void findMoney(String[] sentence ) throws IOException 	{
//		find(sentence, "/nlp/en-ner-money.bin", "Money");
//	}
//	public static void findDate(String[] sentence ) throws IOException 	{
//		find(sentence, "/nlp/en-ner-date.bin", "Date");
//	}
//	public static void findTime(String[] sentence ) throws IOException 	{
//		find(sentence, "/nlp/en-ner-time.bin", "Time");
//	}
//
//	public static void find(String []sentence, String resource, String type) throws IOException 
//	{
//		InputStream is = OpenNLP.class.getResourceAsStream(resource);
//		TokenNameFinderModel model = new TokenNameFinderModel(is);
//		is.close();		 
//		NameFinderME nameFinder = new NameFinderME(model);
//		Span nameSpans[] = nameFinder.find(sentence);
//		if(nameSpans.length > 0){		
//			Statics.Log(">>>>>>>>>>>>>>>>>>>>====="+type+"=====<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//			for(Span s: nameSpans)
//				Statics.Log(s.toString());			
//		}
//	}
//
//	// ======================================================================================
//	//	Sentence detector is for detecting sentence boundaries. Given the following paragraph:
//	//	Sentence detector returns an array of strings with two elements as below.
//	//
//	//		Hi. How are you? This is Mike.
//	//		Hi. How are you? 
//	//		This is Mike.
//	// ======================================================================================
//	public static String[] SentenceDetect(String paragraph ) throws InvalidFormatException,
//	IOException {
//		Statics.Log("=====Sentences=====");
//		InitSentenceDetector();
//		String sentences[] = sdetector.sentDetect(paragraph);		
//		for (String a : sentences)
//			Statics.Log( a );
//		return sentences;
//	}
//	
//	// ======================================================================================
//	//	Tokens are usually words which are separated by space, but there are exceptions. 
//	//	For example, "isn't" gets split into "is" and "n't, since it is a a brief format of "is not". 
//	// ======================================================================================
//	public static String[] Tokenize(String paragraph ) throws InvalidFormatException, IOException 
//	{
//		Statics.Log("=====Tokens=====");
//		InitTokenizer();
//		String tokens[] = tokenizer.tokenize(paragraph);
////		for (String a : tokens)
////			Statics.Log(a);	 
//		return tokens;
//	}
//	
//
//	// ======================================================================================
//	//  Chunker partitions a sentence into a set of chunks by using the tokens generated by tokenizer.
//	// ======================================================================================
//	public static void POSTag(String input) throws IOException 
//	{
//		Statics.Log("=====POS=====");
//		InitPOSTagger();
//		ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(input));
//
//		String line;
//		while ((line = lineStream.read()) != null) {
//			String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(line);
//			String[] tags = tagger.tag(whitespaceTokenizerLine);	 
//			POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
//			Statics.Log(sample.toString());
//			perfMon.incrementCounter();
//		}
//	}
//	
//	// ======================================================================================
//	// ======================================================================================
//	public static void chunk(String input ) throws IOException 
//	{
//		Statics.Log("=====Chunking=====");
//		InitChunkerME();
//		ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(input));
//	 
////		perfMon.start();
//		String line;
//		String whitespaceTokenizerLine[] = null;
//		String[] tags = null;
//		while ((line = lineStream.read()) != null) {
//			whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE.tokenize(line);
//			tags = tagger.tag(whitespaceTokenizerLine);	 
//			POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
////			Statics.Log(sample.toString());
//			perfMon.incrementCounter();
//		}
////		perfMon.stopAndPrintFinalResult();
//	 
//		
//		
//		
//		String result[] = chunkerME.chunk(whitespaceTokenizerLine, tags);
//	 
////		for (String s : result)
////			Statics.Log(s);
//	 
//		Span[] span = chunkerME.chunkAsSpans(whitespaceTokenizerLine, tags);
////		for (Span s : span)
////			Statics.Log(s.toString());
//	}
//	
//	// ======================================================================================
//	// ======================================================================================
////	public static void Parse() throws InvalidFormatException, IOException 
////	{
////		// http://sourceforge.net/apps/mediawiki/opennlp/index.php?title=Parser#Training_Tool
////		InputStream is = OpenNLP.class.getResourceAsStream("/nlp/en-parser-chunking.bin");
////		InputStream is = new FileInputStream("en-parser-chunking.bin"); 
////		ParserModel model = new ParserModel(is);
////		Parser parser = ParserFactory.create(model);
////		String sentence = "Programcreek is a very huge and useful website.";
////		Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
////		for (Parse p : topParses)
////			p.show();
////		is.close();
////	 
////	}
//
//	
//	private static void InitSentenceDetector() {
//		if(sdetector != null){
//			try {
//				// always start with a model, a model is learned from training data
//				InputStream is = OpenNLP.class.getResourceAsStream("/nlp/en-sent.bin");
//				SentenceModel model = new SentenceModel(is);
//				sdetector = new SentenceDetectorME(model);
//				is.close();
//			} 
//			catch (InvalidFormatException e) { e.printStackTrace();}
//			catch (IOException e) { e.printStackTrace();}
//		}
//	}
//	private static void InitTokenizer() {
//		if(tokenizer != null){
//			try {
//				InputStream is = OpenNLP.class.getResourceAsStream("/nlp/en-token.bin");
//				TokenizerModel model = new TokenizerModel(is);
//				tokenizer = new TokenizerME(model);
//				is.close();
//			} 
//			catch (InvalidFormatException e) { e.printStackTrace();}
//			catch (IOException e) { e.printStackTrace();}
//		}
//	}
//
//	private static void InitPOSTagger() {
//		if(tagger != null){
//			try {
//				ClassPathResource cpr = new ClassPathResource("/nlp/en-pos-maxent.bin");
//				POSModel model = new POSModelLoader().load(cpr.getFile());
//				tagger = new POSTaggerME(model);	
//	
//			} 
//			catch (InvalidFormatException e) { e.printStackTrace();}
//			catch (IOException e) { e.printStackTrace();}
//		}
//	}
//
//	private static void InitChunkerME() {
//		if(chunkerME != null){
//			try {
//				// chunker
//				InputStream is = OpenNLP.class.getResourceAsStream("/nlp/en-chunker.bin");
//				ChunkerModel cModel = new ChunkerModel(is);	 
//				chunkerME = new ChunkerME(cModel);			
//			} 
//			catch (InvalidFormatException e) { e.printStackTrace();}
//			catch (IOException e) { e.printStackTrace();}
//		}
//	}
//
//
//	
}
