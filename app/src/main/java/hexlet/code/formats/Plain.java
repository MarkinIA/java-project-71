package hexlet.code.formats;

import hexlet.code.Operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Plain {
    public static String formatToPlain(Map<String, List<Object>> data) {
        return data.entrySet().stream()
                .map(entry -> {
                    List<Object> stepList = entry.getValue();
                    if (stepList.contains(Operations.STAYED)) {
                        return "";
                    }
                    if (stepList.contains(Operations.CHANGED)) {
                        return "Property '" + entry.getKey() + "' was updated. From "
                                + checkComplexity(stepList.get(0)) + " to " + checkComplexity(stepList.get(1));
                    } else {
                        if (stepList.contains(Operations.REDUCED)) {
                            return "Property '" + entry.getKey() + "' was removed";
                        } else {
                            return "Property '" + entry.getKey()
                                    + "' was added with value: " + checkComplexity(stepList.get(0));
                        }
                    }
                })
                .filter(entry -> !entry.isEmpty())
                .collect(Collectors.joining("\n"));
    }

    public static String checkComplexity(Object obj) {
        if (obj instanceof Map || obj instanceof Arrays || obj instanceof ArrayList) {
            return "[complex value]";
        } else {
            return obj instanceof String ? "'" + obj + "'" : String.valueOf(obj);
        }
    }
}
