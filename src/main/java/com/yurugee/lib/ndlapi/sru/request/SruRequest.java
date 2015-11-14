package com.yurugee.lib.ndlapi.sru.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.z3950.zing.cql.CQLNode;

import com.yurugee.lib.ndlapi.exception.ConfigurationException;

/**
 * @author taka
 * SRUのリクエストを表すクラス
 * このクラスをSruAccessクラスで送信して、検索を行う。
 */
public class SruRequest {

	/**
	 * @author taka
	 *　内部で利用するEnum　パラメータを表す。
	 */
	static enum KEYLIST {
		OPERATION("operation"), VERSION("version"), QUERY("query"), START_RECORD(
				"startRecord"), MAX_RECORDS("maximumRecords"), RECORD_PACKING(
				"recordPacking"), RECORD_SCHEMA("recordSchema"), SORT_KEY(
				"sortKeys"), INPROCESS("inprocess"), ONLY_BIB("onlyBib");

		public String key;

		private KEYLIST(String _key) {
			this.key = _key;
		}

	}

	/**
	 * @author taka
	 * recordPackingの選択要素を表す
	 * XML xml
	 * STRING string
	 * のいずれかを選択する
	 * 任意項目
	 */
	public static enum RecordPacking {

		XML("xml"), STRING("string");

		public String type;

		private RecordPacking(String _type) {
			this.type = _type;
		}
	}

	/**
	 * @author taka
	 *　recordingSchemaの選択要素を表す
	 *　DC dc
	 *  DCNDL dcndl
	 *  DCNDL dcndl_simple
	 *  のいずれかを選択する
	 *  任意項目
	 */
	public static enum RecordSchema {
		DC("dc"), DCNDL("dcndl"), DCNDL_SIMPLE("dcndl_simple");

		public String schema;

		private RecordSchema(String _schema) {
			this.schema = _schema;
		}
	}

	public static class SortKey {

		public static enum Path {
			TITLE("title"), CREATOR("creator"), CREATED_DATE("created_date"), MODIFIED_DATE(
					"modified_date");

			public String path;

			private Path(String _path) {
				this.path = _path;
			}
		}
		public static enum SortOrder {
			DESC("0"), ASC("1");

			public String order;

			private SortOrder(String _order) {
				this.order = _order;
			}

		}

		private Path path;

		private SortOrder ascending;

		public void setAscending(SortOrder ascending) {
			this.ascending = ascending;
		}

		public void setPath(Path path) {
			this.path = path;
		}

		public String toString() {
			String value = path.path + ",";
			if (ascending != null) {
				value = value + ascending.order;
			}

			return value;
		}

	}

	public static enum Version {
		VER_1_1("1.1"), VER_1_2("1.2");

		public String version;

		private Version(String _version) {
			this.version = _version;
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

	private static final String DELI = "&";

	private String operation = "searchRetrieve";

	private Version version;

	private int startRecords = 0;

	private int maximumRecords = 0;

	private RecordPacking recordPacking = null;

	private RecordSchema recordSchema = null;

	private List<SortKey> sortKeys = new ArrayList<SortKey>();

	private boolean inprocess = false;

	private boolean onlyBib = false;

	private CQLNode cql;

	public void addSortKeys(SortKey sortKey) {
		this.sortKeys.add(sortKey);
	}

	private List<String> getQueryList() throws ConfigurationException, UnsupportedEncodingException {
		List<String> query = new ArrayList<String>();

		query.add(itemToString(KEYLIST.OPERATION, operation));

		if (version != null) {
			query.add(itemToString(KEYLIST.VERSION, version.version));
		}

		if (cql != null) {
			query.add(itemToString(KEYLIST.QUERY, cql.toCQL()));
		} else {
			String msg = "CQL項目は必須です";
			throw new ConfigurationException(msg);
		}

		if (startRecords != 0) {
			query.add(itemToString(KEYLIST.START_RECORD,
					String.valueOf(startRecords)));
		}

		if (maximumRecords != 0) {
			query.add((itemToString(KEYLIST.MAX_RECORDS,
					String.valueOf(maximumRecords))));
		}

		if (recordPacking != null) {
			query.add(itemToString(KEYLIST.RECORD_PACKING, recordPacking.type));
		}

		if (recordSchema != null) {
			query.add(itemToString(KEYLIST.RECORD_SCHEMA, recordSchema.schema));
		}

		if (sortKeys.size() > 0) {
			
			if(version == Version.VER_1_2){
				String msg = "SortKeysはバージョンは1.1のみです";
				throw new ConfigurationException(msg);
			}
			
			query.add(itemToString(KEYLIST.SORT_KEY, getSorkKeyString()));
		}

		if (inprocess) {
			query.add(itemToString(KEYLIST.INPROCESS, "true"));
		}

		if (onlyBib) {
			query.add(itemToString(KEYLIST.ONLY_BIB, "true"));
		}

		return query;

	}

	public String getQueryString() throws ConfigurationException, UnsupportedEncodingException {

		return String.join(DELI, getQueryList());

	}

	private String getSorkKeyString() {
		return String.join(" ", sortKeys.stream().map(p -> p.toString())
				.collect(Collectors.toList()));
	}

	private String itemToString(KEYLIST key, String value) throws UnsupportedEncodingException {
		return key.key + "=" + URLEncoder.encode(value,"UTF-8");
	}


	public void setCql(CQLNode cql) {
		this.cql = cql;
	}

	public void setInprocess(boolean inprocess) {
		this.inprocess = inprocess;
	}

	public void setMaximumRecords(int maximumRecords) {
		this.maximumRecords = maximumRecords;
	}

	public void setOnlyBib(boolean onlyBib) {
		this.onlyBib = onlyBib;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void setRecordPacking(RecordPacking recordPacking) {
		this.recordPacking = recordPacking;
	}

	public void setRecordSchema(RecordSchema recordSchema) {
		this.recordSchema = recordSchema;
	}

	public void setStartRecords(int startRecords) {
		this.startRecords = startRecords;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

}
