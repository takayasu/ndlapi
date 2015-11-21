package com.yurugee.lib.ndlapi.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XPathUtil {
	
	private static XPathFactory xFactory;
	private static XPath xPath;


	static {
			xFactory = XPathFactory.newInstance();
			xPath = xFactory.newXPath();
	}
	
	public static String getSingleElementForXPath(String xpath,Element elm) throws XPathExpressionException{
		return xPath.evaluate(xpath, elm);
	}

	public static NodeList getElementsForXPath(String xpath,Element elm) throws XPathExpressionException{
		return (NodeList)xPath.evaluate(xpath, elm, XPathConstants.NODESET);
	}
	
	

}
