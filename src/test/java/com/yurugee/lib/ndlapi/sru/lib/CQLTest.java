package com.yurugee.lib.ndlapi.sru.lib;

import org.junit.Test;
import org.z3950.zing.cql.CQLAndNode;
import org.z3950.zing.cql.CQLNode;
import org.z3950.zing.cql.CQLRelation;
import org.z3950.zing.cql.CQLTermNode;
import org.z3950.zing.cql.ModifierSet;

public class CQLTest {

	@Test
	public void test() {
		// Building a parse-tree by hand
		CQLNode n1 = new CQLTermNode("dc.author", new CQLRelation("="),
				"kernighan");
		CQLNode n2 = new CQLTermNode("dc.title", new CQLRelation("all"),
				"elements style");
		CQLNode root = new CQLAndNode(n1, n2,new ModifierSet("and"));
		System.out.println(root.toCQL());

	}
	
	@Test
	public void test2() {
		// Building a parse-tree by hand
		CQLNode n1 = new CQLTermNode("dc.author", new CQLRelation("="),
				"kernighan");
		CQLNode n2 = new CQLTermNode("dc.author", new CQLRelation("="),
				"elements style");
		CQLNode root = new CQLAndNode(n1, n2,new ModifierSet("and"));
		System.out.println(root.toCQL());

	}

}
