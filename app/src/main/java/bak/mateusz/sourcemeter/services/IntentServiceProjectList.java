package bak.mateusz.sourcemeter.services;

import java.util.List;

import bak.mateusz.sourcemeter.model.Project;

/**
 * Created by user on 2016.07.18..
 */
public class IntentServiceProjectList {
    List<Project> projectsList;

    public IntentServiceProjectList(List<Project> projectsList) {

        this.projectsList = projectsList;
    }

    public List<Project> getProjectsList() {
        return projectsList;
    }

}
