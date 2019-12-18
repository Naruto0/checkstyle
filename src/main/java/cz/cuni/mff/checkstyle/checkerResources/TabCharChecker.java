package cz.cuni.mff.checkstyle.checkerResources;

import cz.cuni.mff.checkstyle.Common.Project;
import java.io.File;

public class TabCharChecker implements Checker {

    @Override
    public void check(String attribute) {
        File file = Project.programFile;
    }
}
