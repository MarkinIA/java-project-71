import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestApp {
    public static String expectedStylish;
    public static String expectedPlain;

    public static String expectedJSON;

    @BeforeAll
    static void setString() {
        expectedStylish = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";

        expectedPlain = "\n"
                + "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";

        expectedJSON = "{\"chars1\":[[\"a\",\"b\",\"c\"],\"STAYED\"],"
                + "\"chars2\":[[\"d\",\"e\",\"f\"],false,\"CHANGED\"],\"checked\":[false,true,\"CHANGED\"],"
                + "\"default\":[null,[\"value1\",\"value2\"],\"CHANGED\"],\"id\":[45,null,\"CHANGED\"],"
                + "\"key1\":[\"value1\",\"REDUCED\"],\"key2\":[\"value2\",\"ADDED\"],"
                + "\"numbers1\":[[1,2,3,4],\"STAYED\"],"
                + "\"numbers2\":[[2,3,4,5],[22,33,44,55],\"CHANGED\"],\"numbers3\":[[3,4,5],\"REDUCED\"],"
                + "\"numbers4\":[[4,5,6],\"ADDED\"],\"obj1\":[{\"nestedKey\":\"value\",\"isNested\":true},\"ADDED\"],"
                + "\"setting1\":[\"Some value\",\"Another value\",\"CHANGED\"],\"setting2\":[200,300,\"CHANGED\"],"
                + "\"setting3\":[true,\"none\",\"CHANGED\"]}";
    }

    @Test
    void testStylishJSON() throws IOException {


        Path resourceDirectory1 = Paths.get("src", "test", "resources", "file1.json");
        Path resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json");

        assertThat(Differ.generate(Parser.parse(resourceDirectory1), Parser.parse(resourceDirectory2), "stylish"))
                .isEqualTo(expectedStylish);
    }

    @Test
    void testPlainJSON() throws IOException {


        Path resourceDirectory1 = Paths.get("src", "test", "resources", "file1.json");
        Path resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json");

        assertThat(Differ.generate(Parser.parse(resourceDirectory1), Parser.parse(resourceDirectory2), "plain"))
                .isEqualTo(expectedPlain);
    }

    @Test
    void testStylishYAML() throws IOException {

        Path resourceDirectory1 = Paths.get("src", "test", "resources", "file1.yaml");
        Path resourceDirectory2 = Paths.get("src", "test", "resources", "file2.yaml");

        assertThat(Differ.generate(Parser.parse(resourceDirectory1), Parser.parse(resourceDirectory2), "stylish"))
                .isEqualTo(expectedStylish);
    }

    @Test
    void testPlainYAML() throws IOException {

        Path resourceDirectory1 = Paths.get("src", "test", "resources", "file1.yaml");
        Path resourceDirectory2 = Paths.get("src", "test", "resources", "file2.yaml");

        assertThat(Differ.generate(Parser.parse(resourceDirectory1), Parser.parse(resourceDirectory2), "plain"))
                .isEqualTo(expectedPlain);
    }

    @Test
    void testJsonToJson() throws IOException {

        Path resourceDirectory1 = Paths.get("src", "test", "resources", "file1.json");
        Path resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json");

        assertThat(Differ.generate(Parser.parse(resourceDirectory1), Parser.parse(resourceDirectory2), "json"))
                .isEqualTo(expectedJSON);
    }

    @Test
    void testYamlToJson() throws IOException {

        Path resourceDirectory1 = Paths.get("src", "test", "resources", "file1.yaml");
        Path resourceDirectory2 = Paths.get("src", "test", "resources", "file2.yaml");

        assertThat(Differ.generate(Parser.parse(resourceDirectory1), Parser.parse(resourceDirectory2), "json"))
                .isEqualTo(expectedJSON);
    }

}
