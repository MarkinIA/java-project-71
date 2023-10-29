package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;

public class Differ {
    public static String generate(Map<String, Object> fileMap1, Map<String, Object> fileMap2, String format)
            throws JsonProcessingException {
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
                    middleMap.put(key, Arrays.asList(fileMap1.get(key)
                            , fileMap2.get(key), Operations.CHANGED));
                } else {
                    middleMap.put(key, Arrays.asList(fileMap1.get(key), Operations.STAYED));
                }
            }
        });
        return Formatter.pickStyle(middleMap, format);
    }
}
