import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestApp {
    private static String getResourceByPath(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src", "test", "resources", file)));
    }

    private static String getFilePath(String file) {
        return Paths.get("src", "test", "resources", file).toString();
    }

    @Test
    public void testStylishJSON() throws Exception {
        String expected = getResourceByPath("expectedStylish.txt");

        assertThat(Differ.generate(getFilePath("file1.json"), getFilePath("file2.json"), "stylish"))
                .isEqualTo(expected);
    }

    @Test
    public void testPlainJSON() throws Exception {
        String expected = getResourceByPath("expectedPlain.txt");

        assertThat(Differ.generate(getFilePath("file1.json"), getFilePath("file2.json"), "plain"))
                .isEqualTo(expected);
    }

    @Test
    public void testStylishYAML() throws Exception {
        String expected = getResourceByPath("expectedStylish.txt");

        assertThat(Differ.generate(getFilePath("file1.yml"), getFilePath("file2.yml"), "stylish"))
                .isEqualTo(expected);
    }

    @Test
    public void testPlainYAML() throws Exception {
        String expected = getResourceByPath("expectedPlain.txt");

        assertThat(Differ.generate(getFilePath("file1.yml"), getFilePath("file2.yml"), "plain"))
                .isEqualTo(expected);
    }

    @Test
    public void testJsonToJson() throws Exception {
        assertThat(Differ.generate(getFilePath("file1.json"), getFilePath("file2.json"), "json"))
                .isEqualTo(getResourceByPath("expectedJsonBackUp.json"));
    }

    @Test
    public void testYamlToJson() throws Exception {
        assertThat(Differ.generate(getFilePath("file1.yml"), getFilePath("file2.yml"), "json"))
                .isEqualTo(getResourceByPath("expectedJsonBackUp.json"));
    }

}
