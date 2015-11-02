package com.yurugee.lib.ndlapi.sru.request.item;

public class Item {
	private ItemType type;
	private String value;

	public void setType(ItemType type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static enum ItemType {

		DATA_PROVIDER("dpip"), DATA_PRIVIDER_GROUP("dpgroupid"), TITLE("title"), CREATOR(
				"creator"), PUBLISHER("publisher"), NDC("ndc"), NDLC("ndlc"), DESCRIPTION(
				"description"), SUBJECT("subject"), ISBN("isbn"), ISSN("issn"), JPNO(
				"jpno"), FROM("from"), UNTIL("until"), ANYWHERE("anywhere"), ITEM_NO(
				"item_no"), MEDIA_TYPE("mediatype"), SORTBY("sortby"), ;

		public String type;

		private ItemType(String _type) {
			this.type = _type;
		}

	}

}
