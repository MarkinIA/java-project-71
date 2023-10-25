import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestApp {

    @Test
    void testTake() throws IOException {

        Path resourceDirectory1 = Paths.get("src", "test", "resources", "file1.json");
        Path resourceDirectory2 = Paths.get("src", "test", "resources", "file2.json");

        String actualContent1 = Files.readString(resourceDirectory1);
        String actualContent2 = Files.readString(resourceDirectory2);

        //String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  + timeout: 20\n"
                + "  - timeout: 50\n"
                + "  + verbose: true\n"
                + "}";

        assertThat(Differ.generate(actualContent1, actualContent2)).isEqualTo(expected);
    }

}
