package jp.co.sparkworks.testhelper.generater;

import jp.co.sparkworks.testhelper.datastruct.TableData;

public class SQLGenerater {

	public static String generater(TableData table) {
		if (table == null || table.getTableData().size() == 0) {
			return "NO DATA!";
		}

		StringBuffer tableStringBuffer = new StringBuffer();

		table.getTableData().stream().forEach(row -> {

			// 一行のSQL出力する
			StringBuffer rowStringBuffer = new StringBuffer();

			rowStringBuffer.append("INSERT INTO " + table.getTableName().toLowerCase() + "(");
			// まず項目
			row.getColumnData().stream().forEach(column -> {
				// if (!column.isAutoIncrement()) {
				rowStringBuffer.append(column.getName() + ", ");
				// }
			});

			rowStringBuffer.deleteCharAt(rowStringBuffer.toString().length() - 2);
			rowStringBuffer.append(") VALUES (");

			// 後データ
			row.getColumnData().stream().forEach(column -> {
				// if (!column.isAutoIncrement()) {
				switch (column.getColumnType()) {
				case DIGIT:
					rowStringBuffer.append(column.getValue() + ", ");

					break;
				case STRING:
				case DATETIME:
					if (column.getValue() == null) {
						rowStringBuffer.append("null, ");
					} else {
						rowStringBuffer.append("'" + column.getValue() + "', ");
					}

					break;
				}
				// }
			});

			rowStringBuffer.deleteCharAt(rowStringBuffer.toString().length() - 2);

			// 最後
			rowStringBuffer.append(");\n");

			tableStringBuffer.append(rowStringBuffer.toString());
		});

		return tableStringBuffer.toString();
	}

}
