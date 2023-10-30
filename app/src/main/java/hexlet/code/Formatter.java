package hexlet.code;

import hexlet.code.formats.JsonFormat;
import hexlet.code.formats.Plain;
import hexlet.code.formats.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    private static final String PLAIN_FORMAT = "plain";
    private static final String JSON_FORMAT = "json";
    public static String pickStyle(Map<String, List<Object>> data, String format) throws IOException {
        if (format.equals(PLAIN_FORMAT)) {
            return Plain.formatToPlain(data);
        } else if (format.equals(JSON_FORMAT)) {
            return JsonFormat.formatToJSON(data);
        }
        return Stylish.formatToStylish(data);
    }
}
