package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(Map<String, Object> fileMap1, Map<String, Object> fileMap2)
            throws JsonProcessingException {
        Map<String, String> middleMap = new HashMap<>();

        for (String map2 : fileMap2.keySet()) {
            if (fileMap1.containsKey(map2)) {
                if (fileMap1.get(map2).equals(fileMap2.get(map2))) {
                    middleMap.put(map2 + ": " + fileMap2.get(map2), "  ");
                } else {
                    middleMap.put(map2 + ": " + fileMap1.get(map2), "- ");
                    middleMap.put(map2 + ": " + fileMap2.get(map2), "+ ");
                }
            } else {
                middleMap.put(map2 + ": " + fileMap2.get(map2), "+ ");
            }
        }

        for (String map1 : fileMap1.keySet()) {
            if (!fileMap2.containsKey(map1)) {
                middleMap.put(map1 + ": " + fileMap1.get(map1), "- ");
            }
        }

        String differ = "{\n" + middleMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(m -> {
                    return "  " + m.getValue() + m.getKey() + "\n";
                })
                .collect(Collectors.joining()) + "}";

        return differ;
    }
}
