package cz.cuni.mff.checkstyle.Common;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Project {

    public static File programFile;
    private Path root_folder;
    public File config;
    public File headerFile;

    public Project(String path) {
        this.root_folder = Paths.get(path);
        this.config = loadFile("checkstyle.config");
        programFile = loadFile("MyClass.java");
    }

    private File loadFile(String child) {
        return new File(root_folder.toFile(), child);
    }

    public void setHeader(String name) {
        this.headerFile = loadFile(name);
    }
}