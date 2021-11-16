package com.paydi.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import io.sentry.Sentry;

//import com.billpos.module.CustomException;


public class XMLUtils implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(XMLUtils.class);
	private Sentry sentry;
	public static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
    
    public static Document convertStringToXMLDocument(String xmlString){

    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        try
        {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
        	Sentry.captureException(e);
//        	throw new CustomException(e.getMessage());
        }
        return doc;
    }
    
    public static String getTagValueOfTagName(Node node, String tagName) {
    	String tagValue;
    	try {
    		if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                tagValue = XMLUtils.getTagValue(tagName, element);
            }else {
            	tagValue = "NOT ELEMENT NODE";
            }
    	}catch(Exception e) {
    		tagValue = "ERROR Function getTagValue";
    	}
        return tagValue;
    }
    
    public static String xsltTrasfromation(String xml, String xsl) throws TransformerException {
    	
    	 StringWriter writer = new StringWriter();
    	 StreamResult result = new StreamResult(writer);
    	 TransformerFactory tFactory = TransformerFactory.newInstance();
    	 StreamSource source_xsl = new StreamSource(new StringReader(xsl));
    	 StreamSource source_xml = new StreamSource(new StringReader(xsl));

 		Transformer transformer;
 		StringBuffer strResult= new StringBuffer();
 		strResult.append("<html><head></head><body style=\"font-size:12.0pt; font-family:Times New Roman\">" );
		try {
			transformer = tFactory.newTransformer(source_xsl);
	 		transformer.transform(source_xml,result);
	 		if (writer.toString() != null) {
	 			strResult.append(writer.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", ""));
			}else {
				strResult.append("ERROR XSL Trasfromation!!!!!");
			}
	 		
	 			
		} catch (TransformerConfigurationException e) {
			Sentry.captureException(e);
			strResult.append(e.getMessage());
		}finally {
			
			strResult.append("</body></html>");
			
		}
		return strResult.toString();
    	
    }
    
    
    public static void makeupFile (String file) throws IOException {
		File mFile = new File(file);
		FileInputStream fis = new FileInputStream(mFile);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String result = "";
		String line = "";
		while( (line = br.readLine()) != null){
		 result = result + line; 
		}

		result = "<html><head></head><body style=\"font-size:12.0pt; font-family:Times New Roman\">" 
				+ result.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "") 
				+ "</body></html>";

		mFile.delete();
		FileOutputStream fos = new FileOutputStream(mFile);
		fos.write(result.getBytes());
		fos.flush();
	}

}