package hexlet.code;

import hexlet.code.formats.Plain;
import hexlet.code.formats.Stylish;

import java.util.*;

public class Formatter {
        public static String pickStyle (Map<String, List<Object>> data, String format) {
        if (format.equals("plain")) {
            return Plain.formatToPlain(data);
        }
        return Stylish.formatToStylish(data);
    }
}
