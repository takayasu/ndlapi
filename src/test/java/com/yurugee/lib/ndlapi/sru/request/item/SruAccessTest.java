package com.yurugee.lib.ndlapi.sru.request.item;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.xml.sax.SAXException;
import org.z3950.zing.cql.CQLNode;

import com.yurugee.lib.ndlapi.exception.ConfigurationException;
import com.yurugee.lib.ndlapi.sru.SruAccess;
import com.yurugee.lib.ndlapi.sru.request.SruRequest;
import com.yurugee.lib.ndlapi.sru.request.SruRequest.Version;
import com.yurugee.lib.ndlapi.sru.request.item.CQLCondition.SortBy;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.ItemType;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.ListCondition;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.MatchCondition;
import com.yurugee.lib.ndlapi.sru.response.SruResponse;

public class SruAccessTest {

	@Test
	public void test() throws ConfigurationException, UnsupportedOperationException, UnsupportedEncodingException, IOException, NumberFormatException, XPathExpressionException, SAXException {

SruRequest request = new SruRequest();

		List<String> values = new ArrayList<String>();
		values.add("世界");
		values.add("地図");
		CQLNode node = CQLFactory.createCQLNode(ItemType.TITLE, MatchCondition.NONE, ListCondition.EQUAL, values);


		CQLNode sortNode = CQLFactory.addSortNode(node, SortBy.ISSUED_DATE_DESC);
		System.out.println(sortNode.toCQL());


		request.setCql(sortNode);
		request.setVersion(Version.VER_1_2);

		System.out.println(request.getQueryString());

		SruResponse res = SruAccess.send(request);

		System.out.println(res.getResult());
		System.out.println(res.getCount());

	}

}
