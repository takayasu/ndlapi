package com.yurugee.lib.ndlapi.sru.response;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class SruResponse {

	private static final String COUNT_PATH = "//searchRetrieveResponse/numberOfRecords";

	private String result;
	private Document doc;
	private int count;

	private static DocumentBuilder builder ;
	private static XPathFactory xFactory;
	private static XPath xPath;


	static {
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			xFactory = XPathFactory.newInstance();
			xPath = xFactory.newXPath();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}


	public SruResponse(String result) throws SAXException, IOException, NumberFormatException, XPathExpressionException {
		this.result = result;
		doc = builder.parse(new ByteArrayInputStream(result.getBytes("UTF-8")));
		count = Integer.parseInt(xPath.evaluate(COUNT_PATH, doc));
	}

	public String getResult(){
		return result;
	}

	public int getCount(){
		return count;
	}

	public String getSingleElementForXPath(String xpath) throws XPathExpressionException{
		return xPath.evaluate(xpath, doc);
	}

	public NodeList getElementsForXPath(String xpath) throws XPathExpressionException{
		return (NodeList)xPath.evaluate(xpath, doc, XPathConstants.NODESET);
	}


}
