package hexlet.code;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.Arrays;

public class Differ {
    public static String generate(Map<String, Object> fileMap1, Map<String, Object> fileMap2, String format)
            throws IOException {
        Map<String, List<Object>> middleMap = new TreeMap<>();
        Set<String> keys = new TreeSet<>();
        keys.addAll(fileMap1.keySet());
        keys.addAll(fileMap2.keySet());

        keys.forEach(key -> {
            if (fileMap1.containsKey(key) && !fileMap2.containsKey(key)) {
                middleMap.put(key, Arrays.asList(fileMap1.get(key), Operations.REDUCED));
            } else if (!fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                middleMap.put(key, Arrays.asList(fileMap2.get(key), Operations.ADDED));
            } else {
                if (!String.valueOf(fileMap1.get(key)).equals(String.valueOf(fileMap2.get(key)))) {
                    middleMap.put(key, Arrays.asList(fileMap1.get(key), fileMap2.get(key), Operations.CHANGED));
                } else {
                    middleMap.put(key, Arrays.asList(fileMap1.get(key), Operations.STAYED));
                }
            }
        });
        return Formatter.pickStyle(middleMap, format);
    }
}
