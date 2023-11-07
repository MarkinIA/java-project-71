package hexlet.code;

import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String path1, String path2, String format)
            throws Exception {
        Map<String, Object> fileMap1 = Parser.parse(Paths.get(path1).toAbsolutePath());
        Map<String, Object> fileMap2 = Parser.parse(Paths.get(path2).toAbsolutePath());
        return Formatter.pickStyle(Comparison.getDifferences(fileMap1, fileMap2), format);
    }

    public static String generate(String path1, String path2)
            throws Exception {
        return generate(path1, path2, "stylish");
    }
}
