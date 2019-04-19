package jp.co.sparkworks.testhelper;

import java.util.ArrayList;
import java.util.List;

import jp.co.sparkworks.testhelper.datastruct.TableData;
import jp.co.sparkworks.testhelper.dbaccess.DBExecutor;
import jp.co.sparkworks.testhelper.generater.CSVGenerater;
import jp.co.sparkworks.testhelper.generater.SQLGenerater;
import jp.co.sparkworks.testhelper.input.Reader;
import jp.co.sparkworks.testhelper.output.Writer;

public class DBHelper {

	private static String connectionString = null;

	public static String getConnectionString() {
		return connectionString;
	}

	public static void setConnectionString(String connectionString) {
		DBHelper.connectionString = connectionString;
	}

	public static void generatarSQL(int startVersion, String... tableNames) throws Throwable {

		for (String tableName : tableNames) {
			System.out.println("[" + tableName + "] InsertSQL ");

			// 全データ取得
			TableData table = DBExecutor.executeQueryByTableName(tableName);

			if (table == null || table.getTableData().size() == 0) {
				System.out.println("NO DATA!");
			} else {
				// SQL作成
				String insertSQL = SQLGenerater.generater(table);

				// ファイル出力
				System.out.println(insertSQL);
				Writer.writerSQL("V" + startVersion++ + "__" + tableName, insertSQL);
			}

		}
	}

	public static void generatarCSV(String... tableNames) throws Throwable {

		for (String tableName : tableNames) {
			System.out.println("[" + tableName + "] CSV ");

			// 全データ取得
			TableData table = DBExecutor.executeQueryByTableName(tableName);

			if (table == null || table.getTableData().size() == 0) {
				System.out.println("NO DATA!");
			} else {
				// CSV作成
				String csvString = CSVGenerater.generater(table);
				System.out.println(csvString);

				// ファイル出力
				Writer.writerCSV(tableName, csvString);
			}

		}
	}

	public static void executeSQL(String foldername) throws Throwable {
		// 全SQL取得
		List<String> list = Reader.readSQL(foldername);

		// SQL実施
		List<String> sqls = new ArrayList<String>();
		for (String sql : list) {
			String exesql = sql.trim();
			if (exesql.endsWith(";")) {
				exesql.replace(";", "");
			}
			sqls.add(exesql);
		}

		DBExecutor.executeUpdate(sqls);

	}

	public static void clearTables(String... tables) throws Throwable {
		for (String tablename : tables) {
			String sql = "delete from " + tablename;
			DBExecutor.executeUpdate(sql);
		}
	}
}
