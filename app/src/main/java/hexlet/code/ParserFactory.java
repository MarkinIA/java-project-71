package hexlet.code;

import hexlet.code.parsers.JsonParser;
import hexlet.code.parsers.YmlParser;

public class ParserFactory {
    public static Parser getParser(String format) throws Exception {
        switch (format) {
            case (".yml"):
                return new YmlParser();
            case (".json"):
                return new JsonParser();
            default:
                throw new Exception("Wrong file format");
        }
    }
}
