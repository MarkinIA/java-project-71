package hexlet.code.formats;

import hexlet.code.Operations;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String formatToStylish(Map<String, List<Object>> data) {
        StringBuilder stylishString = new StringBuilder();
        stylishString.append("{");
        for(Map.Entry<String, List<Object>> entry: data.entrySet()) {
            stylishString.append("\n  ");
            List<Object> stepList = entry.getValue();

            if (stepList.contains(Operations.CHANGED)) {
                stylishString.append("- ").append(entry.getKey()).append(": ").append(stepList.get(0));
                stylishString.append("\n  ");
                stylishString.append("+ ").append(entry.getKey()).append(": ").append(stepList.get(1));
            } else {
                String operation = stepList.get(1).equals(Operations.ADDED) ? "+" : "-";
                stylishString.append(stepList.get(1).equals(Operations.STAYED) ? " " : operation).append(" ")
                        .append(entry.getKey()).append(": ").append(stepList.get(0));
            }
        }
        stylishString.append("\n}");
        return data.isEmpty() ? "" : stylishString.toString();
    }
}
