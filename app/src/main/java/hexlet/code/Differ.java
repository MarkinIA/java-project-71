package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String path1, String path2, String format)
            throws Exception {
        Map<String, Object> data1 = getData(path1);
        Map<String, Object> data2 = getData(path2);
        return Formatter.pickStyle(DiffBuilder.getDifferences(data1, data2), format);
    }

    public static String generate(String path1, String path2)
            throws Exception {
        return generate(path1, path2, "stylish");
    }

    public static Map<String, Object> getData(String filePath) throws Exception {
        Path fullPath = Paths.get(filePath).toAbsolutePath().normalize();
        String fileExtension = getExtension(fullPath);
        String content = Files.readString(fullPath);
        return ParserFactory.getParser(fileExtension).parse(content);
    }

    public static String getExtension(Path filePath) {
        String filePathStr = filePath.getFileName().toString();
        return filePathStr.substring(filePathStr.lastIndexOf("."));
    }
}
