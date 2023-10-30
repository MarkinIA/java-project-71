package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(Path filePath) throws IOException {

        String fileName = filePath.getFileName().toString();
        String format = fileName.substring(fileName.lastIndexOf("."));

        if(filePath.toFile().length() == 0) {
            return new HashMap<>();
        }

        if (format.equals(".yaml")) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();
            return mapper.readValue(filePath.toFile(), new TypeReference<Map<String, Object>>() { });
        } else if (format.equals((".json"))) {
            return new ObjectMapper().readValue(filePath.toFile(), new TypeReference<Map<String, Object>>() { });
        } else {
            return new HashMap<>();
        }
    }
}
