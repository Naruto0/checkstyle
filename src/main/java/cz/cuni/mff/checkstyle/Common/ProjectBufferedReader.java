package cz.cuni.mff.checkstyle.Common;

import java.io.*;

public class ProjectBufferedReader {
    public BufferedReader getFile(File file) {
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {

            return br;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

