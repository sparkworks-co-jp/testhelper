package jp.co.sparkworks.testhelper;

public class Main {

    public static void main(String[] args) throws Throwable {

        String[] targetTables = { //
                "account", //
                "address"//
        };

        //   DBHelper.clearTables(targetTables);

       dothat(targetTables);

       //   DBHelper.executeSQL("times-20171102175245");
    }

    // 両方出力
    static void dothat(String... tableNames) throws Throwable {

        DBHelper.generatarSQL(tableNames);

        DBHelper.generatarCSV(tableNames);
    }
}
