package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String file1, String file2) throws JsonProcessingException {
        StringBuilder differString = new StringBuilder();

        Map<String, Object> fileMap1 =
                new ObjectMapper().readValue(file1, new TypeReference<Map<String, Object>>() { });

        Map<String, Object> fileMap2 =
                new ObjectMapper().readValue(file2, new TypeReference<Map<String, Object>>() { });

        Map<String, String> middleMap = new HashMap<>();

        //differString.append("{\n");
        for (String map2 : fileMap2.keySet()) {
            if (fileMap1.containsKey(map2)) {
                if (fileMap1.get(map2).equals(fileMap2.get(map2))) {
                    //differString.append("   ").append(map2).append(": ").append(fileMap2.get(map2)).append("\n");
                    middleMap.put(map2 + ": " + fileMap2.get(map2), "  ");
                } else {
                    //differString.append(" - ").append(map2).append(": ").append(fileMap1.get(map2)).append("\n");
                    //differString.append(" + ").append(map2).append(": ").append(fileMap2.get(map2)).append("\n");
                    middleMap.put(map2 + ": " + fileMap1.get(map2), "- ");
                    middleMap.put(map2 + ": " + fileMap2.get(map2), "+ ");
                }
            } else {
                //differString.append(" + ").append(map2).append(": ").append(fileMap1.get(map2)).append("\n");
                middleMap.put(map2 + ": " + fileMap2.get(map2), "+ ");
            }
        }

        for (String map1 : fileMap1.keySet()) {
            if (!fileMap2.containsKey(map1)) {
                //differString.append(" - ").append(map1).append(": ").append(fileMap1.get(map1)).append("\n");
                middleMap.put(map1 + ": " + fileMap1.get(map1), "- ");
            }
        }

        String differ = "{\n" + middleMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(m -> {
                    return "  " + m.getValue() + m.getKey() + "\n";
                })
                .collect(Collectors.joining()) + "}";

        //differString.append("}");

        return differ;
    }
}
