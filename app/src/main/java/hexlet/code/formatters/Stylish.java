package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String formatToStylish(Map<String, List<Object>> data) throws Exception {
        StringBuilder stylishString = new StringBuilder();
        stylishString.append("{");
        for (Map.Entry<String, List<Object>> entry: data.entrySet()) {
            stylishString.append("\n  ");
            List<Object> stepList = entry.getValue();
            String operation = stepList.get(stepList.size() - 1).toString();

            switch (operation) {
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
                    throw new Exception("Incorrect operation found");
            }
        }
        stylishString.append("\n}");
        return stylishString.toString();
    }
}
