import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestApp {
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
                .isEqualTo(getData("expectedJsonBackUp.json"));
    }

    @Test
    void testYamlToJson() throws Exception {

        String resourceDirectory1 = Paths.get("src", "test", "resources", "file1.yml").toString();
        String resourceDirectory2 = Paths.get("src", "test", "resources", "file2.yml").toString();

        assertThat(Differ.generate(resourceDirectory1, resourceDirectory2, "json"))
                .isEqualTo(getData("expectedJsonBackUp.json"));
    }

}
