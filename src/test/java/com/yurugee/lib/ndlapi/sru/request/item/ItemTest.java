package com.yurugee.lib.ndlapi.sru.request.item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.z3950.zing.cql.CQLNode;

import com.yurugee.lib.ndlapi.exception.ConfigurationException;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.ConditionOperator;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.ItemType;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.ListCondition;
import com.yurugee.lib.ndlapi.sru.request.item.CQLFactory.MatchCondition;

public class ItemTest {

	@Test
	public void test1() throws ConfigurationException {

		System.out.println(createTestNode1().toCQL());
		
	}
	
	@Test
	public void test2() throws ConfigurationException{
		
		CQLNode node1 = createTestNode1();
		CQLNode node2 = createTestNode2();
		
		CQLNode node = CQLFactory.mergeNode(node1, node2, ConditionOperator.OR);
		
		System.out.println(node.toCQL());
	}
	
	@Test
	public void test3(){
		
		List<String> values = new ArrayList<String>();
		values.add("cli");
		values.add("value2");
		
		try {
			CQLNode node = CQLFactory.createCQLNode(ItemType.NDC, MatchCondition.NONE, ListCondition.EQUAL, values);
		} catch (ConfigurationException e) {
			e.printStackTrace();
			return;
		}
		
		fail();
		
	}
	
	
	@Test
	public void test4() throws ConfigurationException{
		
		List<String> values = new ArrayList<String>();
		values.add("cli");
		values.add("value2");
		
		CQLNode node = CQLFactory.createCQLNode(ItemType.CREATOR, MatchCondition.BEFORE_MATCH, ListCondition.EQUAL, values);
		
		System.out.println(node.toCQL());
	}
	
	private CQLNode createTestNode1() throws ConfigurationException{
		
		List<String> values = new ArrayList<String>();
		values.add("cli");
		values.add("value2");
		
		CQLNode node = CQLFactory.createCQLNode(ItemType.DATA_PROVIDER, MatchCondition.NONE, ListCondition.EQUAL, values);
		return node;
		
	}
	
private CQLNode createTestNode2() throws ConfigurationException{
		
		List<String> values = new ArrayList<String>();
		values.add("cli");
		values.add("value2");
		
		CQLNode node = CQLFactory.createCQLNode(ItemType.TITLE, MatchCondition.NONE, ListCondition.EQUAL, values);
		return node;
		
	}
	

}
