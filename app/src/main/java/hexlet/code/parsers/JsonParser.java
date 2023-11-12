package hexlet.code.parsers;


import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Parser;

import java.util.HashMap;
import java.util.Map;

public class JsonParser implements Parser {

    @Override
    public Map<String, Object> parse(String strData) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();
        try {
            data = mapper.readValue(strData, Map.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}
