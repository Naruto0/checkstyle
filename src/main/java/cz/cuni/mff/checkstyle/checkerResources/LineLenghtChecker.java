package cz.cuni.mff.checkstyle.checkerResources;

import cz.cuni.mff.checkstyle.Common.Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LineLenghtChecker implements Checker{

    private static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
    // "MyClass.java: 4 LineLength exceeded: actual length 102, maximum 101"
    @Override
    public void check(String attribute, Project project) throws IOException {
        for (File mainFile: Project.programFiles){

            String relativePath = mainFile.toString().substring(Project.root_folder.toString().length()+1).replace('\\', '/');

            try(FileReader fr = new FileReader(mainFile); BufferedReader br = new BufferedReader(fr)) {
                if (isInteger(attribute)) {
                    String line = "";
                    int limit = Integer.parseInt(attribute);
                    int lineNum = 1;
                    while ((line = br.readLine()) != null) {
                        int actual = line.length();
                        if (actual > limit) {
                            System.err.println(relativePath + ": " + lineNum + " LineLength exceeded: actual length " + actual + ", maximum " + limit);
                        }
                        lineNum++;
                    }
                }
            }
        }
    }
}
