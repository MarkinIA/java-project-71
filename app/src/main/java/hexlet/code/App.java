package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@Command(name = "genndiff", footer = "Copyright(c) 2023", version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public class App {

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    static String fileName1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    static String fileName2;
    @Option(names = { "-f", "--format"}, paramLabel = "format", defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    String format;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit")
    private boolean usageHelpRequested;
    @Option(names = { "-V", "--version" }, versionHelp = true,
            description = "print version information and exit")
    boolean versionRequested;
    public static void main(String[] args) throws IOException {

        CommandLine commandLine = new CommandLine(new App());
        commandLine.parseArgs(args);
        if (commandLine.isUsageHelpRequested()) {
            commandLine.usage(System.out);
        } else if (commandLine.isVersionHelpRequested()) {
            commandLine.printVersionHelp(System.out);
        }

        String path = "app/tmp/";
        File file1 = new File(path);

        Path path1 = Paths.get(fileName1).toAbsolutePath().normalize();
        Path path2 = Paths.get(fileName2).toAbsolutePath().normalize();

        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        System.out.println(Differ.generate(content1, content2));
    }
}