package com.yurugee.lib.ndlapi.sru.request;

public class SruRequest {

	private String operation = "searchRetrieve";

	private Version version = Version.VER_1_2;

	private int startRecords = 1;

	private int maximumRecords = 200;

	private RecordPacking recordPacking = RecordPacking.STRING;

	private RecordSchema recordSchema = RecordSchema.DC;

	private SortKeys sortKeys;

	private boolean inprocess = false;

	private boolean onlyBib = false;



	public String getQueryString(){
		StringBuilder builder = new StringBuilder();



		return builder.toString();

	}



	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public void setStartRecords(int startRecords) {
		this.startRecords = startRecords;
	}

	public void setMaximumRecords(int maximumRecords) {
		this.maximumRecords = maximumRecords;
	}

	public void setRecordPacking(RecordPacking recordPacking) {
		this.recordPacking = recordPacking;
	}

	public void setRecordSchema(RecordSchema recordSchema) {
		this.recordSchema = recordSchema;
	}

	public void setSortKeys(SortKeys sortKeys) {

		if ("1.1".equals(version)) {
			this.sortKeys = sortKeys;
		}

	}

	public void setInprocess(boolean inprocess) {
		this.inprocess = inprocess;
	}

	public void setOnlyBib(boolean onlyBib) {
		this.onlyBib = onlyBib;
	}

	public static enum Version {
		VER_1_1("1.1"), VER_1_2("1.2");

		public String version;

		private Version(String _version) {
			this.version = _version;
		}

	}

	public static enum RecordPacking {

		XML("xml"), STRING("string");

		public String type;

		private RecordPacking(String _type) {
			this.type = _type;
		}
	}

	public static enum RecordSchema {
		DC("dc"), DCNDL("dcndl"), DCNDL_SIMPLE("dcndl_simple");

		public String schema;

		private RecordSchema(String _schema) {
			this.schema = _schema;
		}
	}

	public static class SortKeys {

		private Path path;
		private SortOrder ascending;

		public void setPath(Path path) {
			this.path = path;
		}

		public void setAscending(SortOrder ascending) {
			this.ascending = ascending;
		}

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

	}

}
