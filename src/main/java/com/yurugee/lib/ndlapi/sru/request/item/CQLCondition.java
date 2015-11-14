package com.yurugee.lib.ndlapi.sru.request.item;

public class CQLCondition {

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
	
	public static String getDate(int year,int month,int day){

		if(year <= 0 || year > 2200){
			year = 1900;
		}

		if(month <0 || month >12){
			month = 1;
		}

		if(day < 0 || day > 31){
			day = 1;
		}

		return String.format("%04%d-%02%d-%02%d", year,month,day);

	}
	
	public static String getDate(int year,int month){
		return getDate(year,month,1);
	}
	
	public static String getDate(int year){
		return getDate(year,1,1);
	}
	
	
	

}
