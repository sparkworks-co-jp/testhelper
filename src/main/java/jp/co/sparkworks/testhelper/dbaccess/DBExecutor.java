package jp.co.sparkworks.testhelper.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import jp.co.sparkworks.testhelper.DBHelper;
import jp.co.sparkworks.testhelper.datastruct.ColumnData;
import jp.co.sparkworks.testhelper.datastruct.ColumnType;
import jp.co.sparkworks.testhelper.datastruct.RowData;
import jp.co.sparkworks.testhelper.datastruct.TableData;

public class DBExecutor {

	static Connection conn = null;

	public static TableData executeQueryByTableName(String tableName) throws Throwable {
		TableData table = executeQuery("SELECT * FROM " + tableName);
		table.setTableName(tableName.toUpperCase());
		return table;
	}

	private static TableData executeQuery(String sql) throws Throwable {
		TableData tableData = new TableData();

		Statement stmt = getConnection().createStatement();
		ResultSet rset = stmt.executeQuery(sql);

		// データ取出
		ResultSetMetaData metaData = rset.getMetaData();

		while (rset.next()) {

			RowData rowData = new RowData();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				ColumnData column = new ColumnData();

				// name
				column.setName(metaData.getColumnName(i));

				// value
				column.setValue(rset.getString(i));

				// recordType
				ColumnType type = null;
				int columnType = metaData.getColumnType(i);

				switch (columnType) {

				case Types.BIT:
				case Types.SMALLINT:
				case Types.INTEGER:
				case Types.BIGINT:
				case Types.FLOAT:
				case Types.DOUBLE:
				case Types.DECIMAL:
					type = ColumnType.DIGIT;// 数字なら"'"附かない

					break;
				case Types.CHAR:
				case Types.NCHAR:
				case Types.VARCHAR:
				case Types.NVARCHAR:
					type = ColumnType.STRING;

					break;
				case Types.TIMESTAMP:
				case Types.DATE:
				case Types.TIME:
					type = ColumnType.DATETIME;

					break;
				default:
					type = ColumnType.STRING;
				}

				// System.out.println(columnType + " -> " + type);

				column.setColumnType(type);

				// isAutoIncrement
				column.setAutoIncrement(metaData.isAutoIncrement(i));

				rowData.getColumnData().add(column);
			}

			tableData.getTableData().add(rowData);
		}

		// クローズ
		closeResultSet(rset);
		closeStatement(stmt);
		closeConnection(conn);

		return tableData;
	}

	public static int executeUpdate(String... sqls) throws Throwable {
		return executeUpdate(Arrays.asList(sqls));
	}

	public static int executeUpdate(List<String> sqls) throws Throwable {

		Statement stmt = getConnection().createStatement();
		int effectRows = 0;

		for (String sql : sqls) {
			System.out.println("executeUpdate -> " + sql);
			effectRows = effectRows + stmt.executeUpdate(sql);
		}

		// クローズ
		closeStatement(stmt);
		closeConnection(conn);

		return effectRows;
	}

	static Connection getConnection() throws Throwable {
		if (conn == null || conn.isClosed()) {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DBHelper.getConnectionString());// TODO
		}
		return conn;
	}

	private static void closeResultSet(ResultSet rset) {
		try {
			rset.close();
		} catch (Exception ex) {
		}
	}

	private static void closeStatement(Statement stmt) {
		try {
			stmt.close();
		} catch (Exception ex) {
		}
	}

	private static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (Exception ex) {
		}
	}

}
