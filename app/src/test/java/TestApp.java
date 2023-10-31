import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class TestApp {
    public static String expectedStylish;
    public static String expectedPlain;

    public static String expectedJSON;

    public static String expectedNullToStylish;
    public static String expectedNullToPlain;
    public static String expectedNullToJson;

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

        expectedPlain = "Property 'chars2' was updated. From [complex value] to false\n"
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

        expectedNullToStylish = "{\n"
                + "  + chars1: [a, b, c]\n"
                + "  + chars2: false\n"
                + "  + checked: true\n"
                + "  + default: [value1, value2]\n"
                + "  + id: null\n"
                + "  + key2: value2\n"
                + "  + numbers1: [1, 2, 3, 4]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  + setting1: Another value\n"
                + "  + setting2: 300\n"
                + "  + setting3: none\n"
                + "}";

        expectedNullToPlain = "Property 'chars1' was added with value: [complex value]\n"
                + "Property 'chars2' was added with value: false\n"
                + "Property 'checked' was added with value: true\n"
                + "Property 'default' was added with value: [complex value]\n"
                + "Property 'id' was added with value: null\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers1' was added with value: [complex value]\n"
                + "Property 'numbers2' was added with value: [complex value]\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was added with value: 'Another value'\n"
                + "Property 'setting2' was added with value: 300\n"
                + "Property 'setting3' was added with value: 'none'";

        expectedNullToJson = "{\"chars1\":[[\"a\",\"b\",\"c\"],\"ADDED\"],"
                + "\"chars2\":[false,\"ADDED\"],"
                + "\"checked\":[true,\"ADDED\"],"
                + "\"default\":[[\"value1\",\"value2\"],\"ADDED\"],"
                + "\"id\":[null,\"ADDED\"],"
                + "\"key2\":[\"value2\",\"ADDED\"],"
                + "\"numbers1\":[[1,2,3,4],\"ADDED\"],"
                + "\"numbers2\":[[22,33,44,55],\"ADDED\"],"
                + "\"numbers4\":[[4,5,6],\"ADDED\"],"
                + "\"obj1\":[{\"nestedKey\":\"value\",\"isNested\":true},\"ADDED\"],"
                + "\"setting1\":[\"Another value\",\"ADDED\"],\"setting2\":[300,\"ADDED\"],"
                + "\"setting3\":[\"none\",\"ADDED\"]}";
    }

    @Test
    void testStylishJSON() throws IOException {


        String resourceDirectory1 = Paths.get("src", "test", "resources", "file1.json").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json").toString();

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "stylish"))
                .isEqualTo(expectedStylish);
    }

    @Test
    void testPlainJSON() throws IOException {


        String resourceDirectory1 = Paths.get("src", "test", "resources", "file1.json").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json").toString();

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "plain"))
                .isEqualTo(expectedPlain);
    }

    @Test
    void testStylishYAML() throws IOException {

        String resourceDirectory1 = Paths.get("src", "test", "resources", "file1.yml").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.yml").toString();

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "stylish"))
                .isEqualTo(expectedStylish);
    }

    @Test
    void testPlainYAML() throws IOException {

        String resourceDirectory1 = Paths.get("src", "test", "resources", "file1.yml").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.yml").toString();

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "plain"))
                .isEqualTo(expectedPlain);
    }

    @Test
    void testJsonToJson() throws IOException {

        String resourceDirectory1 = Paths.get("src", "test", "resources", "file1.json").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json").toString();

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "json"))
                .isEqualTo(expectedJSON);
    }

    @Test
    void testYamlToJson() throws IOException {

        String resourceDirectory1 = Paths.get("src", "test", "resources", "file1.yml").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.yml").toString();

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "json"))
                .isEqualTo(expectedJSON);
    }

    @Test
    void testNullToNull() throws IOException {

        String resourceDirectory1 = Paths.get("src", "test", "resources", "fileNull.json").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "fileNull.json").toString();

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "json"))
                .isEqualTo("{}");
    }

    @Test
    void testNullToFilled() throws IOException {

        String resourceDirectory1 = Paths.get("src", "test", "resources", "fileNull.json").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json").toString();

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "stylish"))
                .isEqualTo(expectedNullToStylish);
        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "plain"))
                .isEqualTo(expectedNullToPlain);
        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "json"))
                .isEqualTo(expectedNullToJson);
    }

}
