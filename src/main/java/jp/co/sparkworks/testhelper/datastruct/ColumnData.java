package jp.co.sparkworks.testhelper.datastruct;

public class ColumnData {

	private String name;
	private String value;
	private ColumnType columnType;
	private boolean isAutoIncrement;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ColumnType getColumnType() {
		return columnType;
	}

	public void setColumnType(ColumnType columnType) {
		this.columnType = columnType;
	}

	public boolean isAutoIncrement() {
		return isAutoIncrement;
	}

	public void setAutoIncrement(boolean isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}

	public String toString() {
		return name + "-" + value + "-" + columnType + "-" + isAutoIncrement;
	}

}
