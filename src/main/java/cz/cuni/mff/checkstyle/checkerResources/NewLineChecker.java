package cz.cuni.mff.checkstyle.checkerResources;

import cz.cuni.mff.checkstyle.Common.Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class NewLineChecker implements Checker {
    @Override
    public void check(String attribute, Project project) throws IOException {
        for(File mainFile: Project.programFiles) {

            String relativePath = mainFile.toString().substring(Project.root_folder.toString().length()+1).replace('\\', '/');

            try (FileReader fr = new FileReader(mainFile);
                 BufferedReader br = new BufferedReader(fr)) {
                String LastLine = "";
                while (true) {
                    String current = br.readLine();
                    if (current != null) {
                        LastLine = current;
                    } else {
                        break;
                    }
                }
                if (!LastLine.equals("\n")) {
                    System.err.println(relativePath + ": does not contain newline at the end of file");
                }
            }
        }
    }
}
