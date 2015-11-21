package com.yurugee.lib.ndlapi.oaipmh.response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OAIResponse {

	private String RESUMPTIONTOKENTEXTXPATH = "//OAI-PMH/ListRecords/resumptionToken/text()";
	private String RESUMPTIONTOKENSIZEXPATH = "//OAI-PMH/ListRecords/resumptionToken/@completeListSize";
	private String RECORDXPATH = "//OAI-PMH/ListRecords/record";

	private DocumentBuilderFactory factory = DocumentBuilderFactory
			.newInstance();
	private DocumentBuilder builder = null;

	private XPathFactory xpathFactory = XPathFactory.newInstance();
	private XPath xpath = xpathFactory.newXPath();

	private String responseXml;

	private String resumptionToken;

	private int count;
	private int completeListSize;

	public OAIResponse(String xml) throws ParserConfigurationException,
			UnsupportedEncodingException, SAXException, IOException,
			XPathExpressionException {
		responseXml = xml;
		builder = factory.newDocumentBuilder();

		Document doc = builder.parse(new ByteArrayInputStream(xml
				.getBytes("UTF-8")));

		String token = (String) xpath.evaluate(RESUMPTIONTOKENTEXTXPATH,
				doc, XPathConstants.STRING);

		if(StringUtils.isNotEmpty(token)){
			resumptionToken = token.trim();
		}


		String size = (String) xpath.evaluate(RESUMPTIONTOKENSIZEXPATH, doc,
				XPathConstants.STRING);
		completeListSize = Integer.parseInt(size);

		NodeList list = (NodeList) xpath.evaluate(RECORDXPATH, doc,
				XPathConstants.NODESET);
		count = list.getLength();

	}

	public boolean isNext() {
		if (StringUtils.isNotEmpty(resumptionToken)) {
			return true;
		}

		return false;
	}

	public String getResumptionToken() {
		return resumptionToken;
	}

	public int getCount() {
		return count;
	}

	public int getCompleteListSize() {
		return completeListSize;
	}

}
