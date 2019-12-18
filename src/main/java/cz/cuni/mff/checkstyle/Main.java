package cz.cuni.mff.checkstyle;

import cz.cuni.mff.checkstyle.Common.Project;
import cz.cuni.mff.checkstyle.checkerResources.Checker;
import cz.cuni.mff.checkstyle.checkerResources.CheckerProvider;
import cz.cuni.mff.checkstyle.checkerResources.CheckingMethod;

import java.io.*;
import java.util.regex.Pattern;

public final class Main {
    /*
    private static void checkNewline(File mainFile){
        try(FileReader fr = new FileReader(mainFile);
            BufferedReader br = new BufferedReader(fr) ){
                String LastLine = "";
                boolean running = true;
                while(true) {
                    String current = br.readLine();
                    if(current!=null){
                        LastLine = current;
                    }else{
                        break;
                    }
                }
                if (!LastLine.equals("\n"))
                {
                    System.err.println(mainFile.getName()+ ": does not contain newline at the end of file");
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void checkPackageFormat(File mainFile){
        // implemt checke
        //Pattern.compile("").matcher("").matches()
        try(FileReader f2 = new FileReader(mainFile);
            BufferedReader b2 = new BufferedReader(f2)){
            String javaLine ="";
            // int lineNum = 1;
            while((javaLine=b2.readLine())!=null) {
                String [] expressions  = javaLine.split(" ");
                if (expressions[0].equals("package")){
                    if (!Pattern.compile("^[a-z]+(\\.[a-zA-Z_][a-zA-Z0-9_]*)*$").matcher(expressions[1]).matches())
                    {
                        System.err.println(mainFile.getName() + ": wrong package format");
                    }
                }
            }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    private static void checkTabChar(File mainFile){
        try(FileReader f2 = new FileReader(mainFile);
            BufferedReader b2 = new BufferedReader(f2)){
            String javaLine ="";
            int lineNum = 1;
            while((javaLine=b2.readLine())!=null) {
                char[] lineCharacters = javaLine.toCharArray();
                for (int i= 0; i < lineCharacters.length; i++) {
                    if (lineCharacters[i] == '\t') {
                        System.err.println(mainFile.getName() + ": contains tab char at " + lineNum + ":" + i);
                    }
                }
                lineNum ++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    public static void main(final String[] args) throws IOException {
        Project project = new Project(args[0]);

        File config = project.config;
        File mainFile = project.programFile;

        try(FileReader fr = new FileReader(config);
        BufferedReader br = new BufferedReader(fr)){
            String line  = "";
            while ((line = br.readLine())!=null)
            {
                String[] configItem = line.split("=");

                CheckingMethod method;

                switch(configItem[0]){
                    case "TabChar": method = CheckingMethod.TAB_CHAR;
                        break;
                    case "PackageFormat" : method = CheckingMethod.FORMAT;
                        break;
                    case "LineLength" : method = CheckingMethod.LINE_LENGTH;
                        break;
                    case "NewlineAtEnd" : method = CheckingMethod.NEW_LINE_AT_END;
                        break;
                    case "CheckHeader" : method = CheckingMethod.HEADER;
                        break;
                    default:
                        method = null;
                }
                Checker checker = CheckerProvider.getChecker(method);

                if (checker!=null&&configItem.length==2){
                    checker.check(configItem[1]);
                } else if (checker!=null){
                    checker.check(null);
                }
            }
        }
    }


    }