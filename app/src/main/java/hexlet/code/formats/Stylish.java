package hexlet.code.formats;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String formatToStylish(Map<String, List<Object>> data) {
        StringBuilder stylishString = new StringBuilder();
        stylishString.append("{");
        for (Map.Entry<String, List<Object>> entry: data.entrySet()) {
            stylishString.append("\n  ");
            List<Object> stepList = entry.getValue();

            switch (stepList.get(stepList.size() - 1).toString()) {
                case ("CHANGED"):
                    stylishString.append("- ").append(entry.getKey()).append(": ").append(stepList.get(0));
                    stylishString.append("\n  ");
                    stylishString.append("+ ").append(entry.getKey()).append(": ").append(stepList.get(1));
                    break;
                case ("ADDED"):
                    stylishString.append("+").append(" ")
                            .append(entry.getKey()).append(": ").append(stepList.get(0));
                    break;
                case ("REDUCED"):
                    stylishString.append("-").append(" ")
                            .append(entry.getKey()).append(": ").append(stepList.get(0));
                    break;
                case ("STAYED"):
                    stylishString.append("  ")
                            .append(entry.getKey()).append(": ").append(stepList.get(0));
                    break;
                default:
                    break;
            }
        }
        stylishString.append("\n}");
        return stylishString.toString();
    }
}
