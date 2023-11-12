import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestApp {
    private static String expectedJSON;

    @BeforeAll
    static void setString() {
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

    static String getData(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src", "test", "resources", file)));
    }

    @Test
    void testStylishJSON() throws Exception {


        String resourceDirectory1 = Paths.get("src", "test", "resources", "file1.json").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json").toString();
        String expected = getData("expectedStylish.txt");

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "stylish"))
                .isEqualTo(expected);
    }

    @Test
    void testPlainJSON() throws Exception {
        String resourceDirectory1 = Paths.get("src", "test", "resources", "file1.json").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json").toString();
        String expected = getData("expectedPlain.txt");

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "plain"))
                .isEqualTo(expected);
    }

    @Test
    void testStylishYAML() throws Exception {
        String resourceDirectory1 = Paths.get("src", "test", "resources", "file1.yml").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.yml").toString();
        String expected = getData("expectedStylish.txt");

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "stylish"))
                .isEqualTo(expected);
    }

    @Test
    void testPlainYAML() throws Exception {

        String resourceDirectory1 = Paths.get("src", "test", "resources", "file1.yml").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.yml").toString();
        String expected = getData("expectedPlain.txt");

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "plain"))
                .isEqualTo(expected);
    }

    @Test
    void testJsonToJson() throws Exception {

        String resourceDirectory1 = Paths.get("src", "test", "resources", "file1.json").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json").toString();

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "json"))
                .isEqualTo(expectedJSON);
    }

    @Test
    void testYamlToJson() throws Exception {

        String resourceDirectory1 = Paths.get("src", "test", "resources", "file1.yml").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.yml").toString();

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "json"))
                .isEqualTo(expectedJSON);
    }

}
