package com.yurugee.lib.ndlapi.sru.request.item;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;


import com.yurugee.lib.ndlapi.sru.request.item.Item.ItemType;
import com.yurugee.lib.ndlapi.sru.request.item.SearchItem.ConditionOperator;

public class ItemTest {

	@Test
	public void test1() {
		
		Item item = new Item();
		item.setType(ItemType.DATA_PROVIDER);
		item.setOperator(ConditionOperator.OR);
		item.addValue("cii");
		
		assertThat(item.getCQLItem(), is("dpid = cii"));
		
	}
	
	@Test
	public void test2(){
		Item item = new Item();
		item.setType(ItemType.DATA_PROVIDER);
		item.setOperator(ConditionOperator.OR);
		item.addValue("cii");
		item.addValue("opac");
		
		assertThat(item.getCQLItem(), is("dpid = cii or opac"));
	}
	
	@Test
	public void test3(){
		Item item = new Item();
		item.setType(ItemType.NDC);
		item.setOperator(ConditionOperator.OR);
		item.addValue("100");
		item.addValue("200");
		
		assertThat(item.getCQLItem(), is("ndc = 100"));
	}

}
