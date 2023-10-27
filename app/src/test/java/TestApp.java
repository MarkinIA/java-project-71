import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestApp {

    @Test
    void testJSON() throws IOException {

        Path resourceDirectory1 = Paths.get("src", "test", "resources", "file1.json");
        Path resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json");

        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 20\n"
                + "  + timeout: 50\n"
                + "  + verbose: true\n"
                + "}";

        assertThat(Differ.generate(Parser.parser(resourceDirectory1)
                , Parser.parser(resourceDirectory2))).isEqualTo(expected);
    }

    @Test
    void testYAML() throws IOException {

        Path resourceDirectory1 = Paths.get("src", "test", "resources", "file1.yaml");
        Path resourceDirectory2 = Paths.get("src", "test", "resources", "file2.yaml");

        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  + timeout: 20\n"
                + "  - timeout: 50\n"
                + "  + verbose: true\n"
                + "}";

        assertThat(Differ.generate(Parser.parser(resourceDirectory1)
                , Parser.parser(resourceDirectory2))).isEqualTo(expected);
    }

}
