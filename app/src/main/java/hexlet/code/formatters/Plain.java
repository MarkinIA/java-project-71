package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Plain {

    private static String templateForChanged = "Property '%s' was updated. From %s to %s";
    private static String templateForAdded = "Property '%s' was added with value: %s";
    private static String templateForReduced = "Property '%s' was removed";
    public static String formatToPlain(Map<String, List<Object>> data) {
        return data.entrySet().stream()
                .map(entry -> {
                    List<Object> stepList = entry.getValue();
                    String template = "";
                    switch (stepList.get(stepList.size() - 1).toString()) {
                        case ("CHANGED"):
                            template = String.format(templateForChanged, entry.getKey(), checkComplexity(stepList.get(0)), checkComplexity(stepList.get(1)));
                            break;
                        case ("ADDED"):
                            template = String.format(templateForAdded, entry.getKey(), checkComplexity(stepList.get(0)));
                            break;
                        case ("REDUCED"):
                            template = String.format(templateForReduced, entry.getKey());
                            break;
                        case ("STAYED"):
                            template = "";
                            break;
                        default:
                            break;
                    }
                    return template;
                })
                .filter(entry -> !entry.isEmpty())
                .collect(Collectors.joining("\n"));
    }

    public static String checkComplexity(Object obj) {
        if (obj instanceof Map || obj instanceof Arrays || obj instanceof ArrayList) {
            return "[complex value]";
        }
        if (obj instanceof String) {
            return "'" + obj + "'";
        }
        return String.valueOf(obj);
    }
}
