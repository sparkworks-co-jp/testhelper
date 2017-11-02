package jp.co.sparkworks.testhelper.output;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Writer {


    private static String FOLDER = "./output";

    public static void writerSQL(String tableName, String contents) throws Throwable {
        String filename = tableName + ".sql";
        writerToFile(filename, contents);
    }

    public static void writerCSV(String tableName, String contents) throws Throwable {
        String filename = tableName + ".csv";
        writerToFile(filename, contents);
    }

    private static void writerToFile(String filename, String contents) throws Throwable {

        String foldername = getFolderName();
        File folder = new File(foldername);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(getFolderName(), filename);

        FileWriter filewriter = new FileWriter(file);

        filewriter.write(contents);
        filewriter.flush();
        filewriter.close();
    }

    private static String getFolderName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter).toString();
        String foldername = FOLDER + "/times-" + timestamp;
        return foldername;
    }
}
