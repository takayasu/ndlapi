package com.yurugee.lib.ndlapi.sru.request.item;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.z3950.zing.cql.CQLNode;

import com.yurugee.lib.ndlapi.exception.ConfigurationException;
import com.yurugee.lib.ndlapi.sru.SruAccess;
import com.yurugee.lib.ndlapi.sru.request.SruRequest;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.ItemType;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.ListCondition;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.MatchCondition;
import com.yurugee.lib.ndlapi.sru.response.SruResponse;

public class SruAccessTest {

	@Test
	public void test() throws ConfigurationException, UnsupportedOperationException, UnsupportedEncodingException, IOException {
		
SruRequest request = new SruRequest();
		
		List<String> values = new ArrayList<String>();
		values.add("世界");
		values.add("地図");
		CQLNode node = CQLFactory.createCQLNode(ItemType.TITLE, MatchCondition.NONE, ListCondition.EQUAL, values);

		request.setCql(node);
		
		SruResponse res = SruAccess.send(request);
		
		System.out.println(res.getResult());
		
	}

}
