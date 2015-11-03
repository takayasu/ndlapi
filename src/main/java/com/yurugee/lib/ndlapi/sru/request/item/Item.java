package com.yurugee.lib.ndlapi.sru.request.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Item {
	private ItemType type;
	private List<String> values;
	private SearchItem.ConditionOperator operator;

	private static Set<ItemType> ableMultipleItem;

	public Item() {
		values = new ArrayList<String>();
		ableMultipleItem = new HashSet<ItemType>();
		setSetting();
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public void addValue(String value) {

		if (values.size() > 0
				&& !ableMultipleItem.contains(type)) {
			return;
		}

		this.values.add(value);
	}

	public String getCQLItem() {
		return type.type + " = " +String.join(" " + operator.op + " ", values);
	}

	public void setOperator(SearchItem.ConditionOperator _op) {
		this.operator = _op;
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

	private void setSetting() {
		ableMultipleItem.add(ItemType.DATA_PROVIDER);
		ableMultipleItem.add(ItemType.TITLE);
		ableMultipleItem.add(ItemType.CREATOR);
		ableMultipleItem.add(ItemType.PUBLISHER);
		ableMultipleItem.add(ItemType.DESCRIPTION);
		ableMultipleItem.add(ItemType.SUBJECT);
		ableMultipleItem.add(ItemType.ANYWHERE);
		ableMultipleItem.add(ItemType.MEDIA_TYPE);
	}

}
