package hexlet.code.formats;

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
                    String template = "Property '%s' was %s";
                    switch (stepList.get(stepList.size() - 1).toString()) {
                        case ("CHANGED"):
                            template = String.format(template, entry.getKey(), "updated. From "
                                    + checkComplexity(stepList.get(0)) + " to " + checkComplexity(stepList.get(1)));
                            break;
                        case ("ADDED"):
                            template = String.format(template, entry.getKey(), "added with value: "
                                    + checkComplexity(stepList.get(0)));
                            break;
                        case ("REDUCED"):
                            template = String.format(template, entry.getKey(), "removed");
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
