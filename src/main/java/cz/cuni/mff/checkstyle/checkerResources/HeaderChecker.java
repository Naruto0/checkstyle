package cz.cuni.mff.checkstyle.checkerResources;

import cz.cuni.mff.checkstyle.Common.Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

// "src/MyClass.java: Wrong header"
public class HeaderChecker implements Checker {

    @Override
    public void check(String attribute, Project project) throws IOException {
        for(File mainFile: Project.programFiles) {
            String relativePath = mainFile.toString().substring(Project.root_folder.toString().length()+1).replace('\\', '/');
            String headerFile = String.valueOf(Paths.get(Project.root_folder.toString(),attribute));

            try(FileReader fr = new FileReader(mainFile); BufferedReader br = new BufferedReader(fr);
                FileReader hr = new FileReader(headerFile); BufferedReader hb = new BufferedReader(hr)){
                String headerLine = "";
                String fileLine ="";
                while ((headerLine=hb.readLine())!=null){
                    fileLine=br.readLine();
                    if(!headerLine.equals(fileLine)){
                        System.err.println(relativePath + ": Wrong header");
                    }
                }
            }
        }
    }
}
