package cz.cuni.mff.checkstyle;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class MainTest {

    private List<String> runCheckstyleAndGetOutput(final String path) throws IOException {
        File tmp = File.createTempFile("prefix", "suffix");

        System.setErr(new PrintStream(tmp));

        Main.main(new String[] {path});

        return Files.readAllLines(Paths.get(tmp.toURI()));
    }

    @Test
    public void wrongHeaderProjectTest() throws IOException {
        List<String> output = runCheckstyleAndGetOutput(new File (getClass().getClassLoader().getResource("wrongHeaderProject").getFile()).toPath().toString());

        assertEquals("Only one error expected", 1, output.size());
        assertThat(output, containsInAnyOrder("src/MyClass.java: Wrong header"));
    }

    @Test
    public void wrongLineLengthProjectTest() throws IOException {
        List<String> output = runCheckstyleAndGetOutput((new File (getClass().getClassLoader().getResource("wrongLineLengthProject").getFile()).toPath()).toString());

        assertEquals("Two errors expected", 2, output.size());
        assertThat(output, containsInAnyOrder("MyClass.java: 4 LineLength exceeded: actual length 102, maximum 101",
                "MyClass.java: 7 LineLength exceeded: actual length 162, maximum 101"));
    }

    @Test
    public void wrongNewlineAtEndProjectTest() throws IOException {
        List<String> output = runCheckstyleAndGetOutput((new File (getClass().getClassLoader().getResource("wrongNewlineAtEndProject").getFile()).toPath()).toString());

        assertEquals("Only one error expected", 1, output.size());
        assertThat(output, containsInAnyOrder("MyClass.java: does not contain newline at the end of file"));
    }

    @Test
    public void wrongPackageFormatProjectTest() throws IOException {
        List<String> output = runCheckstyleAndGetOutput((new File (getClass().getClassLoader().getResource("wrongPackageFormatProject").getFile()).toPath()).toString());

        assertEquals("Only one error expected", 1, output.size());
        assertThat(output, containsInAnyOrder("MyClass.java: wrong package format"));
    }

    @Test
    public void wrongTabCharProjectTest() throws IOException {
        List<String> output = runCheckstyleAndGetOutput((new File (getClass().getClassLoader().getResource("wrongTabCharProject").getFile()).toPath()).toString());

        assertEquals("Two errors expected", 2, output.size());
        assertThat(output, containsInAnyOrder("MyClass.java: contains tab char at 2:0", "MyClass.java: contains tab char at 4:20"));
    }

    @Test
    public void checkDirsProjectTest() throws IOException {
        List<String> output = runCheckstyleAndGetOutput((new File (getClass().getClassLoader().getResource("checkDirsProject").getFile()).toPath()).toString());
        assertEquals("7 errors expected", 7, output.size());
        assertThat(output, containsInAnyOrder(
                "src2/anotherDir/PigClass.java: Wrong header",
                "src2/anotherDir/PigClass.java: wrong package format",
                "src2/anotherDir/PigClass.java: 8 LineLength exceeded: actual length 151, maximum 120",
                "src2/anotherDir/PigClass.java: contains tab char at 10:0",
                "src2/anotherDir/PigClass.java: contains tab char at 10:1",
                "src2/anotherDir/PigClass.java: does not contain newline at the end of file",
                "src/VeryImportantClass.java: does not contain newline at the end of file"
        ));
    }

}