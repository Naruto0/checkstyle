package cz.cuni.mff.checkstyle.checkerResources;

import cz.cuni.mff.checkstyle.Common.Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TabCharChecker implements Checker {

    @Override
    public void check(String attribute, Project project) throws IOException {
        for (File mainFile : Project.programFiles) {

            String relativePath = mainFile.toString().substring(Project.root_folder.toString().length()+1).replace('\\', '/');

            try (FileReader fr = new FileReader(mainFile); BufferedReader br = new BufferedReader(fr)) {
                String javaLine = "";
                int lineNum = 1;
                while ((javaLine = br.readLine()) != null) {
                    char[] lineCharacters = javaLine.toCharArray();
                    for (int i = 0; i < lineCharacters.length; i++) {
                        if (lineCharacters[i] == '\t') {
                            System.err.println(relativePath + ": contains tab char at " + lineNum + ":" + i);
                        }
                    }
                    lineNum++;
                }
            }
        }
    }
}
