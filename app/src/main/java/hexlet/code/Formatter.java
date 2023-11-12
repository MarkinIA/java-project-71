package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    private static final String PLAIN_FORMAT = "plain";
    private static final String JSON_FORMAT = "json";

    private static final String STYLISH_FORMAT = "stylish";
    public static String pickStyle(Map<String, List<Object>> data, String format) throws Exception {
        return switch (format) {
            case (STYLISH_FORMAT) -> Stylish.formatToStylish(data);
            case (PLAIN_FORMAT) -> Plain.formatToPlain(data);
            case (JSON_FORMAT) -> Json.formatToJSON(data);
            default -> throw new Exception("Incorrect format");
        };
    }
}
