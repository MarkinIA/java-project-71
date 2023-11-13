package hexlet.code.parsers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Parser;

import java.util.HashMap;
import java.util.Map;

public class JsonParser implements Parser {

    @Override
    public final Map<String, Object> parse(String strData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();
        data = mapper.readValue(strData, Map.class);
        return data;
    }
}
