package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;

public class Differ {
    public static String generate(Map<String, Object> fileMap1, Map<String, Object> fileMap2)
            throws JsonProcessingException {
        Map<String, List<String>> middleMap = new TreeMap<>();
        Set<String> keys = new TreeSet<>();
        keys.addAll(fileMap1.keySet());
        keys.addAll(fileMap2.keySet());

        System.out.println(keys);

        keys.forEach(key -> {
           if (fileMap1.containsKey(key) && !fileMap2.containsKey(key)) {
               middleMap.put(key, Arrays.asList(String.valueOf(fileMap1.get(key)), "-"));
           } else if (!fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
               middleMap.put(key, Arrays.asList(String.valueOf(fileMap2.get(key)), "+"));
           } else {
               if (!String.valueOf(fileMap1.get(key)).equals(String.valueOf(fileMap2.get(key)))) {
                   middleMap.put(key, Arrays.asList(String.valueOf(fileMap1.get(key))
                           , String.valueOf(fileMap2.get(key)), "<>"));
               } else {
                   middleMap.put(key, Arrays.asList(String.valueOf(fileMap1.get(key)), "="));
               }
           }
        });

        return Formatter.stylish(middleMap);
    }
}
