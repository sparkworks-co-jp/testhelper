package jp.co.sparkworks.testhelper;

public class Main {

	public static void main(String[] args) throws Throwable {

		// 接続文字列を設定する
		DBHelper.setConnectionString("jdbc:mysql://localhost:3306/goodlunch?useSSL=false&user=root&password=goodlunch");

		/*****************
		 * 全テーブル対象
		 *****************/
		// 全テーブルデータのInsertSQL文作成する
		DBHelper.generatarAllTablesSQL(2001);// flyway用DDLファイルのV番号は2001から
		// 全テーブルデータのcsvファイルに出力する
		DBHelper.generatarAllTablesCSV();

		/*****************
		 * 指定テーブル対象
		 *****************/
		// 操作対象のテーブル指定
//		String[] targetTables = { //
//				"account", //
//				"address"//
//		};

		// 指定テーブルデータ削除する（慎重に！！！）
		// DBHelper.clearTables(targetTables);

		// 指定テーブルデータのInsertSQL文作成する
		// DBHelper.generatarSQL(targetTables);

		// 指定テーブルデータをcsvファイルに出力する
		// DBHelper.generatarCSV(targetTables);

		// 設定outputフォルダにある全部SQL文を実施する
		// DBHelper.executeSQL("sql-20171102175245");
	}

}
