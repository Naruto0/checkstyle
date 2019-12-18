package cz.cuni.mff.checkstyle.checkerResources;

import java.io.File;

public class CheckerProvider {
    public static Checker getChecker(CheckingMethod method) {
        switch(method){
            case TAB_CHAR:
                return new TabCharChecker();
            case NEW_LINE_AT_END:
                return new NewLineChecker();
            case FORMAT:
                return  new PackageFormatChecker();
            case HEADER:
                return  new HeaderChecker();
            case LINE_LENGTH:
                return new LineLenghtChecker();
            default:
                return null;
        }
    }
}