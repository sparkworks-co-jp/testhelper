package jp.co.sparkworks.testhelper.datastruct;

import java.util.ArrayList;
import java.util.List;

public class RowData {

	private List<ColumnData> columnData = new ArrayList<ColumnData>();

	public List<ColumnData> getColumnData() {
		return columnData;
	}

	public void setColumnData(List<ColumnData> columnData) {
		this.columnData = columnData;
	}

	public String toString() {
		return columnData.toString();
	}

}
