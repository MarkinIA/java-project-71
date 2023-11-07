package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(Path filePath) throws Exception {
        if (filePath.toFile().length() == 0) {
            throw new Exception("File is empty");
        }

        switch (getFormat(filePath)) {
            case (".yml"):
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                mapper.findAndRegisterModules();
                return mapper.readValue(filePath.toFile(), new TypeReference<Map<String, Object>>() { });
            case (".json"):
                return new ObjectMapper().readValue(filePath.toFile(), new TypeReference<Map<String, Object>>() { });
            default:
                return new HashMap<>();
        }
    }

    public static String getFormat(Path filePath) {
        String filePathStr = filePath.getFileName().toString();
        return filePathStr.substring(filePathStr.lastIndexOf("."));
    }
}
