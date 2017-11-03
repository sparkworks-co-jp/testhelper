package jp.co.sparkworks.testhelper;

public class Main {

	public static void main(String[] args) throws Throwable {

		// 操作対象のテーブル設定
		String[] targetTables = { //
				"account", //
				"address"//
		};

		// 全テーブルデータ削除する（慎重に！！！）
		// DBHelper.clearTables(targetTables);

		// 全テーブルデータのInsertSQL文作成する
		DBHelper.generatarSQL(targetTables);
		// 全テーブルデータをcsvファイルに出力する
		DBHelper.generatarCSV(targetTables);

		// 設定outputフォルダにある全部SQL文を実施する
		// DBHelper.executeSQL("times-20171102175245");
	}

}
