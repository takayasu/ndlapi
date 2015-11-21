package com.yurugee.lib.ndlapi.sru.request.item;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.z3950.zing.cql.CQLNode;

import com.yurugee.lib.ndlapi.exception.ConfigurationException;
import com.yurugee.lib.ndlapi.sru.SruAccess;
import com.yurugee.lib.ndlapi.sru.request.SruRequest;
import com.yurugee.lib.ndlapi.sru.request.SruRequest.RecordPacking;
import com.yurugee.lib.ndlapi.sru.request.SruRequest.RecordSchema;
import com.yurugee.lib.ndlapi.sru.request.SruRequest.Version;
import com.yurugee.lib.ndlapi.sru.request.item.CQLCondition.SortBy;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.ItemType;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.ListCondition;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.MatchCondition;
import com.yurugee.lib.ndlapi.sru.response.SruResponse;
import com.yurugee.lib.ndlapi.util.XPathUtil;

public class SruAccessTest {

	@Ignore
	@Test
	public void test() throws ConfigurationException,
			UnsupportedOperationException, UnsupportedEncodingException,
			IOException, NumberFormatException, XPathExpressionException,
			SAXException {

		SruRequest request = new SruRequest();

		CQLNode node = createKeywordNode();

		CQLNode sortNode = CQLFactory
				.addSortNode(node, SortBy.ISSUED_DATE_DESC);
		System.out.println(sortNode.toCQL());

		request.setCql(sortNode);
		request.setVersion(Version.VER_1_2);

		System.out.println(request.getQueryString());

		SruResponse res = SruAccess.send(request);

		System.out.println(res.getCount());

	}

	private CQLNode createKeywordNode() throws ConfigurationException {
		List<String> values = new ArrayList<String>();
		values.add("世界");
		values.add("地図");
		CQLNode node = CQLFactory.createCQLNode(ItemType.TITLE,
				MatchCondition.NONE, ListCondition.EQUAL, values);
		return node;
	}
	
	@Ignore
	@Test
	public void test2() throws ConfigurationException,
			UnsupportedOperationException, UnsupportedEncodingException,
			IOException, NumberFormatException, XPathExpressionException,
			SAXException {

		SruRequest request = new SruRequest();

		CQLNode node = createKeywordNode();

		request.setCql(node);
		request.setVersion(Version.VER_1_2);
		
		request.setRecordPacking(RecordPacking.XML);
		request.setRecordSchema(RecordSchema.DCNDL);

		System.out.println(request.getQueryString());

		SruResponse res = SruAccess.send(request);

		outputFile(res.getResult());
	}
	
	@Test
	public void test3() throws ConfigurationException, NumberFormatException, UnsupportedOperationException, UnsupportedEncodingException, XPathExpressionException, IOException, SAXException{
		CQLNode node = createKeywordNode();
		
		SruRequest request = new SruRequest();
		request.setCql(node);
		request.setVersion(Version.VER_1_2);
		
		request.setRecordPacking(RecordPacking.XML);
		request.setRecordSchema(RecordSchema.DCNDL);

		SruResponse res = SruAccess.send(request);
		
//		System.out.println(res.getSingleElementForXPath("//searchRetrieveResponse/records/record/recordData/RDF/BibResource/title"));	
		NodeList list = res.getElementsForXPath("/searchRetrieveResponse/records/record/recordData/RDF/BibResource/title/Description/value/text()");
		
		for (int i = 0; i < list.getLength(); i++) {
			Node title = list.item(i);
			System.out.println(title.getNodeValue());
			
		}
	}

	private void printElements(NodeList list) {

		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			NodeList record = node.getChildNodes();
			for (int j = 0; i < record.getLength(); j++) {
				Node child = record.item(j);

				NodeList recordData = child.getChildNodes();

				for (int k = 1; k < recordData.getLength(); k++) {

					Node recordData1 = recordData.item(k);

					if ("recordData".equals(recordData1.getNodeName())) {
						String srw = recordData1.getTextContent();
						System.out.println(srw);
					}
				}

			}
		}

	}
	
	private void outputFile(String xml){
		long time = System.currentTimeMillis();
		
		File file = new File("data/"+time+".txt");
		try(Writer writer = new FileWriter(file)){
			writer.write(xml);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
