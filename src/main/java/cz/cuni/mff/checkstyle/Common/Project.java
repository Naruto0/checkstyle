package cz.cuni.mff.checkstyle.Common;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Project {

    public static File[] programFiles;
    public static Path root_folder;
    public static File config;


    private static void getFiles(File folder, ArrayList<File> arrayList) {
        for (File file: folder.listFiles()){
            if(file.isDirectory()){
                getFiles(file, arrayList);
            }else{
                if(file.isFile()&& file.toString().endsWith(".java")){
                    arrayList.add(file);
                }
            }
        }
    }

    private static File[] filter(File path){

        ArrayList<File> javaFiles = new ArrayList<>();
        // String[] allFiles = path.list();

        /*for(String file: allFiles){
            if (file.endsWith(".java")){
                javaFiles.add(new File(root_folder.toFile(), file));
            }else if(new File(file).isDirectory()){
                File srcDirectory = new File(file);
                String[] src = srcDirectory.list();
                for (String srcFile: src){
                    if(srcFile.endsWith(".java")){
                        javaFiles.add(new File(srcFile));
                    }
                }
            }
        }*/

        getFiles(path, javaFiles);

        File[] result = javaFiles.toArray(new File[javaFiles.size()]) ;
        return result;
    }

    public Project(String path) {
        root_folder = Paths.get(path);
        File sources = new File(root_folder.toString());
        programFiles = filter(sources);
        config = loadFile("checkstyle.config");
    }


    private File loadFile(String child) {
        return new File(root_folder.toFile(), child);
    }

}