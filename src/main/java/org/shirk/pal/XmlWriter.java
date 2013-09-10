package org.shirk.pal;

import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlWriter {
	
	public void writeXml(Map<String, Boolean> outputData) {
		try {
			// Create the document and root element
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			Element rootElement = document.createElement("answers");
			document.appendChild(rootElement);
			
			// Create each of the child elements and append to the root element
			for (Map.Entry<String, Boolean> entry : outputData.entrySet()) {
				String key = entry.getKey();
				Boolean value = entry.getValue();
				Element childElement = document.createElement(key);
				childElement.setTextContent(value.toString());
				rootElement.appendChild(childElement);
			}
			
			// Create the dom transformer and write to xml file
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult("outputdata.xml");
			transformer.transform(domSource, streamResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
