package cz.cuni.mff.checkstyle;
import cz.cuni.mff.checkstyle.Common.Project;
import cz.cuni.mff.checkstyle.checkerResources.Checker;
import cz.cuni.mff.checkstyle.checkerResources.CheckerProvider;
import cz.cuni.mff.checkstyle.checkerResources.CheckingMethod;
import java.io.*;
import java.util.regex.Pattern;

public final class Main {

    public static void main(final String[] args) throws IOException {
        Project project = new Project(args[0]);

        File config = project.config;

        try(FileReader fr = new FileReader(config);
        BufferedReader br = new BufferedReader(fr)){
            String line  = "";
            while ((line = br.readLine())!=null)
            {
                String[] configItem = line.split("=");

                CheckingMethod method;
                String attribute = null;

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

                if (checker!=null&&configItem.length==2) {
                    attribute = configItem[1];
                }
                checker.check(attribute,project);
            }
        }
    }
}