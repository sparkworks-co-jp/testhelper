package jp.co.sparkworks.testhelper.generater;

import jp.co.sparkworks.testhelper.datastruct.RowData;
import jp.co.sparkworks.testhelper.datastruct.TableData;

public class CSVGenerater {

	public static String generater(TableData table) {
		if (table == null || table.getTableData().size() == 0) {
			return "NO DATA!";
		}

		StringBuffer tableStringBuffer = new StringBuffer();

		// まずタイトル
		RowData firstRow = table.getTableData().get(0);
		firstRow.getColumnData().stream().forEach(column -> {

			tableStringBuffer.append(column.getName() + ",");

		});

		tableStringBuffer.deleteCharAt(tableStringBuffer.toString().length() - 1);
		tableStringBuffer.append("\n");

		// 後データ
		table.getTableData().stream().forEach(row -> {

			// 一行のSQL出力する
			StringBuffer rowStringBuffer = new StringBuffer();

			row.getColumnData().stream().forEach(column -> {

				switch (column.getColumnType()) {
				case DIGIT:
				case STRING:
				case DATETIME:
					rowStringBuffer.append(column.getValue() + ",");

					break;
				}

			});

			rowStringBuffer.deleteCharAt(rowStringBuffer.toString().length() - 1);

			// 最後
			rowStringBuffer.append("\n");

			tableStringBuffer.append(rowStringBuffer.toString());
		});

		return tableStringBuffer.toString();
	}

}
