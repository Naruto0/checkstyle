package cz.cuni.mff.checkstyle.checkerResources;
import cz.cuni.mff.checkstyle.Common.Project;
import cz.cuni.mff.checkstyle.Common.ProjectBufferedReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class PackageFormatChecker implements Checker {
    @Override
    public void check(String attribute, Project project) throws IOException {
        for (File mainFile : Project.programFiles) {

            String relativePath = mainFile.toString().substring(Project.root_folder.toString().length()+1).replace('\\', '/');

            try(FileReader fr = new FileReader(mainFile); BufferedReader br = new BufferedReader(fr))
            {
                String javaLine = "";
                // int lineNum = 1;
                while ((javaLine = br.readLine()) != null) {
                    String[] expressions = javaLine.split(" ");
                    if (expressions[0].equals("package")) {
                        if (!Pattern.compile("^[a-z]+(\\.[a-zA-Z_][a-zA-Z0-9_]*)*$").matcher(expressions[1]).matches()) {
                            System.err.println(relativePath + ": wrong package format");
                        }
                    }
                }
            }
        }
    }
}
