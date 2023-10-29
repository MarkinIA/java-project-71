package hexlet.code.formats;

import hexlet.code.Operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String formatToPlain(Map<String, List<Object>> data) {
        StringBuilder plainString = new StringBuilder();
        for(Map.Entry<String, List<Object>> entry: data.entrySet()) {
            List<Object> stepList = entry.getValue();
            if (stepList.contains(Operations.STAYED)) {
                continue;
            }
            plainString.append("\n");
            if (stepList.contains(Operations.CHANGED)) {
                plainString.append("Property '").append(entry.getKey()).append("' was updated. From ")
                        .append(checkComplexity(stepList.get(0))).append(" to ")
                        .append(checkComplexity(stepList.get(1)));
            } else {
                if (stepList.contains(Operations.REDUCED)) {
                    plainString.append("Property '").append(entry.getKey()).append("' was removed");
                } else {
                    plainString.append("Property '").append(entry.getKey()).append("' was added with value: ")
                            .append(checkComplexity(stepList.get(0)));
                }
            }
        }
        return data.isEmpty() ? "" : plainString.toString();
    }

    public static String checkComplexity(Object obj) {
        if (obj instanceof Map || obj instanceof Arrays || obj instanceof ArrayList) {
            return "[complex value]";
        } else {
            return obj instanceof String ? "'" + obj + "'" : String.valueOf(obj);
        }
    }
}
