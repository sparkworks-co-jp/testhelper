package jp.co.sparkworks.testhelper.datastruct;

import java.util.ArrayList;
import java.util.List;

public class TableData {

	private String tableName;
	private List<RowData> tableData = new ArrayList<RowData>();

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<RowData> getTableData() {
		return tableData;
	}

	public void setTableData(List<RowData> tableData) {
		this.tableData = tableData;
	}

	public String toString() {
		return tableName + "\n" + tableData.toString();
	}

}
