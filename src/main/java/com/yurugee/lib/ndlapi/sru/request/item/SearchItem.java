package com.yurugee.lib.ndlapi.sru.request.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.yurugee.lib.ndlapi.sru.request.item.Item.ItemType;

public class SearchItem {

	private static String DATESEP = "-";
	private static String ERRDATE = "2015-10-01";


	private Map<ItemType,Item> itemMap = new HashMap<ItemType,Item>();

	public void setDataProvider(String id,ConditionOperator ops) {

		addItem(ItemType.DATA_PROVIDER, id,ops);
	}

	public void setDataGroupProvider(String id,ConditionOperator ops) {
		addItem(ItemType.DATA_PRIVIDER_GROUP, id,ops);
	}

	public void setTitle(String title,ConditionOperator ops) {
		addItem(ItemType.TITLE, title,ops);
	}

	public void setCreator(String creator,ConditionOperator ops){
		addItem(ItemType.CREATOR,creator,ops);
	}


	public void setNdc(String ndc,ConditionOperator ops){
		addItem(ItemType.NDC,ndc,ops);
	}

	public void setNdlc(String ndlc,ConditionOperator ops){
		addItem(ItemType.NDLC,ndlc,ops);
	}

	public void setDescription(String description,ConditionOperator ops){
		addItem(ItemType.DESCRIPTION,description,ops);
	}

	public void setSubject(String subject,ConditionOperator ops){
		addItem(ItemType.SUBJECT,subject,ops);
	}

	public void setIsbn(String isbn,ConditionOperator ops){
		addItem(ItemType.ISBN,isbn,ops);
	}

	public void setIssn(String issn,ConditionOperator ops){
		addItem(ItemType.ISSN,issn,ops);
	}

	public void setJpno(String jpno,ConditionOperator ops){
		addItem(ItemType.JPNO,jpno,ops);
	}

	public void setFrom(int year,int month,int day,ConditionOperator ops){
		addItem(ItemType.FROM,consistDate(year, month, day),ops);
	}

	public void setUntil(int year,int month,int day,ConditionOperator ops){
		addItem(ItemType.UNTIL,consistDate(year, month, day),ops);
	}

	public void setAnywhere(String value,ConditionOperator ops){
		addItem(ItemType.ANYWHERE,value,ops);
	}

	public void setItemNo(String itemno,ConditionOperator ops){
		addItem(ItemType.ITEM_NO,itemno,ops);
	}

	public void setMediaType(MediaType media,ConditionOperator ops){
		addItem(ItemType.MEDIA_TYPE,media.no,ops);
	}

	public void setSortBy(SortBy sort,ConditionOperator ops){
		addItem(ItemType.SORTBY,sort.sortby,ops);
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

	public static enum MediaType{
		BOOK("1"),
		ARTICLE("2"),
		NEWSPAPER("3"),
		CHILD_BOOK("4"),
		REFERENCE_INFO("5"),
		DIGITAL_MATERIAL("6"),
		ETC("7"),
		DAISY("8"),
		LEGISLATION("9")
		;

		public String no;

		private MediaType(String _no){
			this.no = _no;
		}
	}


	public static enum SortBy{

		TITLE_ASC("title/sort.ascending"),
		TITLE_DESC("title/sort.descending"),
		CREATOR_ASC("creator/sort.ascending"),
		CREATOR_DESC("creator/sort.descending"),
		CREATED_DATE_ASC("created_date/sort.ascending"),
		CREATED_DATE_DESC("created_date/sort.descending"),
		MODIFIED_DATE_ASC("modified_date/sort.ascending"),
		MODIFIED_DATE_DESC("modified_date/sort.descending"),
		ISSUED_DATE_ASC("issued_date/sort.ascending"),
		ISSUED_DATE_DESC("issued_date/sort.descending"),
		;
		public String sortby;

		private SortBy(String _sortby){
			this.sortby = _sortby;
		}

	}




	private String consistDate(int year,int month,int day){

		if(year <= 0 || year > 2200){
			return ERRDATE;
		}

		if(month <0 || month >12){
			return ERRDATE;
		}

		if(day < 0 || day > 31){
			return ERRDATE;
		}

		return String.format("%04%d-%02%d-%02%d", year,month,day);

	}

	private void addItem(ItemType type, String value,ConditionOperator ops) {
		
		Item item = itemMap.get(type);
		
		if(item == null){
			item = new Item();
			item.setType(type);
			itemMap.put(type,item);
		}
		
		item.addValue(value);
		item.setOperator(ops);


	}

}
