package bak.mateusz.sourcemeter.services;

import android.app.IntentService;
import android.content.Intent;

import java.util.List;

import bak.mateusz.sourcemeter.model.ProjectsListResponse;
import bak.mateusz.sourcemeter.model.Result;

/**
 * Created by user on 2016.07.18..
 */
public class IntentServiceProjectList {
    List<Result> projectsList;

    public IntentServiceProjectList(List<Result> projectsList) {

        this.projectsList = projectsList;
    }

    public List<Result> getProjectsList() {
        return projectsList;
    }

}
