package jp.co.sparkworks.testhelper;

public class Main {

	public static void main(String[] args) throws Throwable {

		// 接続文字列を設定する
		DBHelper.setConnectionString("jdbc:mysql://localhost:3306/goodlunch?useSSL=false&user=root&password=goodlunch");

		// 操作対象のテーブル設定
		String[] targetTables = { //
				"account", //
				"address"//
		};

		// 全テーブルデータ削除する（慎重に！！！）
		// DBHelper.clearTables(targetTables);

		// 全テーブルデータのInsertSQL文作成する
		// flyway用DDLファイルのV番号は2001から
		DBHelper.generatarSQL(2001, targetTables);

		// 全テーブルデータをcsvファイルに出力する
		// DBHelper.generatarCSV(targetTables);

		// 設定outputフォルダにある全部SQL文を実施する
		// DBHelper.executeSQL("sql-20171102175245");
	}

}
