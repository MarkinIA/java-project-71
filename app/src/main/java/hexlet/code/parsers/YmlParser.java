package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import hexlet.code.Parser;

import java.util.Map;

public class YmlParser implements Parser {
    @Override
    public final Map<String, Object> parse(String strData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        return mapper.readValue(strData, Map.class);
    }
}
