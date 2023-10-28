package hexlet.code;

import java.util.*;

public class Formatter {
    public static String stylish(Map<String, List<String>> data) {
        StringBuilder stylishString = new StringBuilder();
        stylishString.append("{");
        for(Map.Entry<String, List<String>> entry: data.entrySet()) {
            stylishString.append("\n  ");
            List<String> stepList = entry.getValue();
            if (stepList.contains("<>")) {
                stylishString.append("- ").append(entry.getKey()).append(": ").append(stepList.get(0));
                stylishString.append("\n  ");
                stylishString.append("+ ").append(entry.getKey()).append(": ").append(stepList.get(1));
            } else {
                stylishString.append(stepList.get(1).equals("=") ? " " : stepList.get(1)).append(" ")
                        .append(entry.getKey()).append(": ").append(stepList.get(0));
            }
        }
        stylishString.append("\n}");
        return stylishString.toString();
    }
}
