package jp.co.sparkworks.testhelper.output;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Writer {


    private static String FOLDER = "./output";

    // 一回の実施でフォルダ名を固定するため、staticに定義している
    private static String timestamp = null;

    public static void writerSQL(String tableName, String contents) throws Throwable {
        String foldername = getFolderName("SQL");
        String filename = tableName + ".sql";
        File file = new File(foldername, filename);
        writerToFile(file, contents);
    }

    public static void writerCSV(String tableName, String contents) throws Throwable {
        String foldername = getFolderName("CSV");
        String filename = tableName + ".csv";
        File file = new File(foldername, filename);
        writerToFile(file, contents);
    }

    private static void writerToFile(File file, String contents) throws Throwable {
        FileWriter filewriter = new FileWriter(file);
        filewriter.write(contents);
        filewriter.flush();
        filewriter.close();
    }

    private static String getFolderName(String prefix) {
        String folderName = FOLDER + "/" + prefix + "-" + getTimeStamp();
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        return folderName;
    }

    private static String getTimeStamp() {
        if (timestamp == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            timestamp = LocalDateTime.now().format(formatter).toString();
        }
        return timestamp;
    }

}
