package org.shirk.pal;

import java.util.Map;
import java.util.TreeMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlHandler extends DefaultHandler {
	
	private String qName;
	private boolean read = false;
	private Map<Integer, String> inputData = new TreeMap<Integer, String>();
	
	public void startElement(String uri, String localName, String qName, 
			Attributes attributes) throws SAXException {
		this.qName = qName;
		read = true;
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (qName.contains("data") && !qName.contains("set") && read) {
			String val = removeNonWordCharacters(new String(ch, start, length));
			Integer index = Integer.valueOf(qName.replace("data", ""));
			inputData.put(index, val.toUpperCase());
			read = false;
		}
	}
	
	private String removeNonWordCharacters(String input) {
		return input.replaceAll("\\W", "");
	}

	public Map<Integer, String> getInputData() {
		return inputData;
	}
	
}
