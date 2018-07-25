# 機能説明

・テーブルの全データをクリアする

・テーブルの全データのinsertSQL文作成する

・テーブルの全データをcsvファイルに出力する

・あるフォルダの全sql（insert/update/delete）文実施する


# 使用方法

まず、接続文字列を設定する


// 接続文字列を設定する
DBHelper.setConnectionString("jdbc:mysql://localhost:3306/goodlunch?useSSL=false&user=root&password=goodlunch");


// 操作対象のテーブル設定

String[] targetTables = { //

		"account", //テーブル名
		
		"address"//テーブル名
};


// 全テーブルデータ削除する（慎重に！！！）

DBHelper.clearTables(targetTables);


// 全テーブルデータのInsert SQL文作成する

DBHelper.generatarSQL(targetTables);



// 全テーブルデータをcsvファイルに出力する

DBHelper.generatarCSV(targetTables);


// outputフォルダにある全部SQL文を実施する

DBHelper.executeSQL("SQL-20171102175245");
