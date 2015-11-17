package com.yurugee.lib.ndlapi.sru.request.item;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.z3950.zing.cql.CQLNode;

import com.yurugee.lib.ndlapi.exception.ConfigurationException;
import com.yurugee.lib.ndlapi.sru.request.SruRequest;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.ItemType;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.ListCondition;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.MatchCondition;

public class SruRequestTest {

	@Test
	public void test() throws ConfigurationException, UnsupportedEncodingException {
		SruRequest request = new SruRequest();

		List<String> values = new ArrayList<String>();
		values.add("世界");
		CQLNode node = CQLFactory.createCQLNode(ItemType.TITLE, MatchCondition.NONE, ListCondition.EQUAL, values);

		request.setCql(node);

		System.out.println(request.getQueryString());

	}

}
