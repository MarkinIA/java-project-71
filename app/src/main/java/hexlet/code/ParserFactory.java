package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.parsers.JsonParser;
import hexlet.code.parsers.YmlParser;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ParserFactory {
    public static Map<String, Object> getParser(String format, String data) throws JsonProcessingException {
        switch (format) {
            case (".yml"):
                Parser parseYML = new YmlParser();
                return parseYML.parse(data);
            case (".json"):
                Parser parseJSON = new JsonParser();
                return parseJSON.parse(data);
            default:
                return new HashMap<>();
        }
    }
}
