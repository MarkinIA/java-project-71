package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Objects;

public class DiffBuilder {
    public static Map<String, List<Object>> getDifferences(
            Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Map<String, List<Object>> diffMap = new TreeMap<>();
        Set<String> keys = new TreeSet<>();
        keys.addAll(firstMap.keySet());
        keys.addAll(secondMap.keySet());

        keys.forEach(key -> {
            if (firstMap.containsKey(key) && !secondMap.containsKey(key)) {
                diffMap.put(key, Arrays.asList(firstMap.get(key), Operations.REDUCED));
            } else if (!firstMap.containsKey(key) && secondMap.containsKey(key)) {
                diffMap.put(key, Arrays.asList(secondMap.get(key), Operations.ADDED));
            } else {
                if (!Objects.equals(firstMap.get(key), secondMap.get(key))) {
                    diffMap.put(key, Arrays.asList(firstMap.get(key), secondMap.get(key), Operations.CHANGED));
                } else {
                    diffMap.put(key, Arrays.asList(firstMap.get(key), Operations.STAYED));
                }
            }
        });

        return diffMap;
    }
}
