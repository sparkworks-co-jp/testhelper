package jp.co.sparkworks.testhelper.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private static String FOLDER = "./output";

    public static List<String> readSQL(String foldername) throws Throwable {

        List<String> list = new ArrayList<String>();

        File dir = new File(FOLDER, foldername);

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("フォルダ[" + dir.getAbsolutePath() + "]存在していません！");
        }

        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File file, String str) {

                // 拡張子を指定する
                if (str.toLowerCase().endsWith("sql")) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        File[] files = dir.listFiles(filter);

        for (File file : files) {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String sql = br.readLine();
            StringBuffer last = null;
            while (sql != null && sql.trim().length() > 0 && !sql.trim().startsWith("#")) {
                sql = sql.trim();
                System.out.println(sql);

                if (sql.toLowerCase().indexOf("insert ") > -1 || sql.toLowerCase().indexOf("update ") > -1
                        || sql.toLowerCase().indexOf("delete ") > -1) {
                    if (last != null) {
                        list.add(last.toString());
                    }
                    last = new StringBuffer();
                    last.append(sql);
                } else {
                    last.append("\n");
                    last.append(sql);
                }

                sql = br.readLine();
            }

            if (last != null) {
                list.add(last.toString());
            }

            br.close();

        }

        return list;

    }

}
