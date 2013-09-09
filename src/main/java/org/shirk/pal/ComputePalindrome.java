package org.shirk.pal;

import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ComputePalindrome {
	
	InputStream is = null;
	static Map<String, String> inputData = null;
	static Map<String, Boolean> outputData = null;
	
	
	public static void main(String[] args) {
		ComputePalindrome cp = new ComputePalindrome();
		
		// Read the input data
		cp.readInputData();
		
		// Compute the palindrome
		cp.compute();
		
		// Write the output data
		cp.outputData();
	}

	
	private void readInputData() {
		try {	
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			is = ComputePalindrome.class.getClassLoader().getResourceAsStream("datafile.xml");
			if (is == null) {
				throw new Exception("InputStream is null");
			}
			XmlHandler handler = new XmlHandler();
			parser.parse(is, handler);
			inputData = handler.getInputData();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	private void compute() {
		Map<String, Boolean> results = new TreeMap<String, Boolean>();
		
		for (Map.Entry<String, String> entry : inputData.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			
			String newKey = "answer" + key.replace("data", "");
			boolean isPal = PalindromeTester.isPalindrome(value);	
			results.put(newKey, isPal);
		}
		outputData = results;
	}
	
	
	private void outputData() {
		XmlWriter writer = new XmlWriter();
		writer.writeXml(outputData);
	}
	
	
	
	
	
}
