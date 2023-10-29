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

    @BeforeAll
    static void setString() {
        expectedStylish = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

        expectedPlain = "\n" +
                "Property 'chars2' was updated. From [complex value] to false\n" +
                "Property 'checked' was updated. From false to true\n" +
                "Property 'default' was updated. From null to [complex value]\n" +
                "Property 'id' was updated. From 45 to null\n" +
                "Property 'key1' was removed\n" +
                "Property 'key2' was added with value: 'value2'\n" +
                "Property 'numbers2' was updated. From [complex value] to [complex value]\n" +
                "Property 'numbers3' was removed\n" +
                "Property 'numbers4' was added with value: [complex value]\n" +
                "Property 'obj1' was added with value: [complex value]\n" +
                "Property 'setting1' was updated. From 'Some value' to 'Another value'\n" +
                "Property 'setting2' was updated. From 200 to 300\n" +
                "Property 'setting3' was updated. From true to 'none'";
    }

    @Test
    void testStylishJSON() throws IOException {


        Path resourceDirectory1 = Paths.get("src", "test", "resources", "file1.json");
        Path resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json");

        assertThat(Differ.generate(Parser.parse(resourceDirectory1)
                , Parser.parse(resourceDirectory2), "stylish")).isEqualTo(expectedStylish);
    }

    @Test
    void testPlainJSON() throws IOException {


        Path resourceDirectory1 = Paths.get("src", "test", "resources", "file1.json");
        Path resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json");

        assertThat(Differ.generate(Parser.parse(resourceDirectory1)
                , Parser.parse(resourceDirectory2), "plain")).isEqualTo(expectedPlain);
    }

    @Test
    void testStylishYAML() throws IOException {

        Path resourceDirectory1 = Paths.get("src", "test", "resources", "file1.yaml");
        Path resourceDirectory2 = Paths.get("src", "test", "resources", "file2.yaml");

        assertThat(Differ.generate(Parser.parse(resourceDirectory1)
                , Parser.parse(resourceDirectory2), "stylish")).isEqualTo(expectedStylish);
    }

}
