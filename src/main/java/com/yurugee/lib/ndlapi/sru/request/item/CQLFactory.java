package com.yurugee.lib.ndlapi.sru.request.item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.z3950.zing.cql.CQLAndNode;
import org.z3950.zing.cql.CQLNode;import org.z3950.zing.cql.CQLRelation;
import org.z3950.zing.cql.CQLTermNode;
import org.z3950.zing.cql.ModifierSet;
import org.z3950.zing.cql.CQLOrNode;

import com.yurugee.lib.ndlapi.exception.ConfigurationException;

public class CQLFactory {
	
	private static Set<ItemType> matchConditionItemList = new HashSet<ItemType>();
	private static Map<ItemType,Set<ListCondition>> listConditionItemMap = new HashMap<ItemType,Set<ListCondition>>();
	private static Set<ItemType> multiConditionItemList = new HashSet<ItemType>();

	static {
		initMatchList();
		initListMap();
		initMultiList();
	}
	
	private static void initMatchList(){
		matchConditionItemList.add(ItemType.TITLE);
		matchConditionItemList.add(ItemType.CREATOR);
		matchConditionItemList.add(ItemType.PUBLISHER);
	}
	
	private static void initListMap(){
		
		Set<ListCondition> all = new HashSet<ListCondition>();
		all.add(ListCondition.ALL);
		all.add(ListCondition.ANY);
		all.add(ListCondition.EQUAL);
		
		Set<ListCondition> anyEqual = new HashSet<ListCondition>();
		anyEqual.add(ListCondition.ANY);
		anyEqual.add(ListCondition.EQUAL);
		
		Set<ListCondition> equal = new HashSet<ListCondition>();
		equal.add(ListCondition.EQUAL);
		
		listConditionItemMap.put(ItemType.DATA_PROVIDER,anyEqual);
		listConditionItemMap.put(ItemType.DATA_PRIVIDER_GROUP,equal);
		listConditionItemMap.put(ItemType.TITLE,all);
		listConditionItemMap.put(ItemType.CREATOR,all);
		listConditionItemMap.put(ItemType.PUBLISHER,all);
		listConditionItemMap.put(ItemType.NDC,equal);
		listConditionItemMap.put(ItemType.NDLC,equal);
		listConditionItemMap.put(ItemType.DESCRIPTION,all);
		listConditionItemMap.put(ItemType.SUBJECT,all);
		listConditionItemMap.put(ItemType.ISBN,equal);
		listConditionItemMap.put(ItemType.ISSN,equal);
		listConditionItemMap.put(ItemType.JPNO,equal);
		listConditionItemMap.put(ItemType.FROM,equal);
		listConditionItemMap.put(ItemType.UNTIL,equal);
		listConditionItemMap.put(ItemType.ANYWHERE,all);
		listConditionItemMap.put(ItemType.ITEM_NO,equal);
		listConditionItemMap.put(ItemType.MEDIA_TYPE,equal);
	}
	
	private static void initMultiList(){
		multiConditionItemList.add(ItemType.DATA_PROVIDER);
		multiConditionItemList.add(ItemType.TITLE);
		multiConditionItemList.add(ItemType.CREATOR);
		multiConditionItemList.add(ItemType.PUBLISHER);
		multiConditionItemList.add(ItemType.DESCRIPTION);
		multiConditionItemList.add(ItemType.SUBJECT);
		multiConditionItemList.add(ItemType.ANYWHERE);
		multiConditionItemList.add(ItemType.MEDIA_TYPE);
	}
	
	
	
	public static CQLNode createCQLNode(ItemType type,MatchCondition match,ListCondition condition,List<String> values) throws ConfigurationException{
		
		if(!check(type,condition,match,values)){
			String msg = "Type:"+type.type+" の指定可能条件を満たしていません";
			throw new ConfigurationException(msg);
		}
		
		return new CQLTermNode(type.type,new CQLRelation(condition.op),createTerm(values, match));
	}
		
	public static CQLNode mergeNode(CQLNode node1,CQLNode node2,ConditionOperator op) throws ConfigurationException{
		
		if(op == ConditionOperator.OR){
			return new CQLOrNode(node1, node2, new ModifierSet(op.op));
		}
		
		if(op == ConditionOperator.AND){
			return new CQLAndNode(node1, node2, new ModifierSet(op.op));
		}
		
		String msg = "結合条件はAND/ORのみが指定できます";		
		throw new ConfigurationException(msg);
		
	}
	
	private static boolean check(ItemType type,ListCondition condition,MatchCondition match,List<String> values){
		
		if(type == null || condition == null || match == null || values == null){
			return false;
		}
		
		//一致条件
		if(match != MatchCondition.NONE){
			if(!matchConditionItemList.contains(type)){
				return false;
			}
		}
		
		//論理条件
		Set<ListCondition> set = listConditionItemMap.get(type);
		if(!set.contains(condition)){
			return false;
		}
		
		//複数条件
		if(values.size() >1){
			if(!multiConditionItemList.contains(type)){
				return false;
			}
		}
		
		return true;
	}
	
	private static String createTerm(List<String> values,MatchCondition condition){
		
		StringBuilder builder = new StringBuilder();
		builder.append(condition.op);
		builder.append("(");
		builder.append(String.join(" ", values));
		builder.append(")");
		
		return builder.toString();
	}
	
	
	public static enum ItemType {

		DATA_PROVIDER("dpid"), DATA_PRIVIDER_GROUP("dpgroupid"), TITLE("title"), CREATOR(
				"creator"), PUBLISHER("publisher"), NDC("ndc"), NDLC("ndlc"), DESCRIPTION(
				"description"), SUBJECT("subject"), ISBN("isbn"), ISSN("issn"), JPNO(
				"jpno"), FROM("from"), UNTIL("until"), ANYWHERE("anywhere"), ITEM_NO(
				"item_no"), MEDIA_TYPE("mediatype"), SORTBY("sortby"), ;

		public String type;

		private ItemType(String _type) {
			this.type = _type;
		}

	}
	
	public static enum ConditionOperator{
		OR("or"),
		AND("and"),
		;
		
		public String op;
		
		private ConditionOperator(String _op){
			this.op = _op;
		}
	}
	
	public static enum ListCondition {
		ALL("all"),
		ANY("any"),
		EQUAL("="),
		;
		
		public String op;
		
		private ListCondition(String _op){
			this.op = _op;
		}
		
	}
	
	public static enum MatchCondition {
		BEFORE_MATCH("^"),
		ALL_MATCH("exact"),
		NONE("");
		
		private String op;
		
		private MatchCondition(String _op){
			this.op = _op;
		}
		
		
	}
	
	
}
