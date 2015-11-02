package com.yurugee.lib.ndlapi.sru.request.item;

import java.util.ArrayList;
import java.util.List;

import com.yurugee.lib.ndlapi.sru.request.item.Item.ItemType;

public class SearchItem {

	private static String DATESEP = "-";
	private static String ERRDATE = "2015-10-01";


	private List<Item> itemList = new ArrayList<Item>();

	public void setDataProvider(String id) {

		addItem(ItemType.DATA_PROVIDER, id);
	}

	public void setDataGroupProvider(String id) {
		addItem(ItemType.DATA_PRIVIDER_GROUP, id);
	}

	public void setTitle(String title) {
		addItem(ItemType.TITLE, title);
	}

	public void setCreator(String creator){
		addItem(ItemType.CREATOR,creator);
	}


	public void setNdc(String ndc){
		addItem(ItemType.NDC,ndc);
	}

	public void setNdlc(String ndlc){
		addItem(ItemType.NDLC,ndlc);
	}

	public void setDescription(String description){
		addItem(ItemType.DESCRIPTION,description);
	}

	public void setSubject(String subject){
		addItem(ItemType.SUBJECT,subject);
	}

	public void setIsbn(String isbn){
		addItem(ItemType.ISBN,isbn);
	}

	public void setIssn(String issn){
		addItem(ItemType.ISSN,issn);
	}

	public void setJpno(String jpno){
		addItem(ItemType.JPNO,jpno);
	}

	public void setFrom(int year,int month,int day){
		addItem(ItemType.FROM,consistDate(year, month, day));
	}

	public void setUntil(int year,int month,int day){
		addItem(ItemType.UNTIL,consistDate(year, month, day));
	}

	public void setAnywhere(String value){
		addItem(ItemType.ANYWHERE,value);
	}

	public void setItemNo(String itemno){
		addItem(ItemType.ITEM_NO,itemno);
	}

	public void setMediaType(MediaType media){
		addItem(ItemType.MEDIA_TYPE,media.no);
	}

	public void setSortBy(SortBy sort){

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

		TITLE_ASC("title")
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

	private void addItem(ItemType type, String value) {
		Item item = new Item();
		item.setType(type);
		item.setValue(value);

		itemList.add(item);
	}

}
