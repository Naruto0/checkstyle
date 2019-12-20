package cz.cuni.mff.checkstyle.checkerResources;

import cz.cuni.mff.checkstyle.Common.Project;

import java.io.File;
import java.io.IOException;

public interface Checker {
        void check (String attribute, Project project) throws IOException;
    }
