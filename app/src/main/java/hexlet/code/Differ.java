package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;

public class Differ {
    public static StringBuilder generate(Map<String, Object> fileMap1, Map<String, Object> fileMap2)
            throws JsonProcessingException {
        Map<String, List<String>> middleMap = new TreeMap<>();
        Set<String> keys = new TreeSet<>();
        keys.addAll(fileMap1.keySet());
        keys.addAll(fileMap2.keySet());

        System.out.println(keys);

        keys.forEach(key -> {
           if (fileMap1.containsKey(key) && !fileMap2.containsKey(key)) {
               middleMap.put(key, Arrays.asList(fileMap1.get(key).toString(), "-"));
           } else if (!fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
               middleMap.put(key, Arrays.asList(fileMap2.get(key).toString(), "+"));
           } else {
               if (!fileMap1.get(key).equals(fileMap2.get(key))) {
                   middleMap.put(key, Arrays.asList(fileMap1.get(key).toString(), fileMap2.get(key).toString(), "<>"));
               } else {
                   middleMap.put(key, Arrays.asList(fileMap1.get(key).toString(), "="));
               }
           }
        });

        System.out.println(middleMap);
        return Formatter.stylish(middleMap);
    }
}
