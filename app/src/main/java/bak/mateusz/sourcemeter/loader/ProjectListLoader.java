package bak.mateusz.sourcemeter.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.util.List;

import bak.mateusz.sourcemeter.model.ProjectsListResponse;
import bak.mateusz.sourcemeter.model.Result;
import bak.mateusz.sourcemeter.network.ServiceGenerator;
import bak.mateusz.sourcemeter.network.SourceMeterService;
import retrofit2.Call;

/**
 * Created by user on 2016.07.13..
 */
public class ProjectListLoader extends AsyncTaskLoader<List<Result>> {
    private List<Result> projectList;

    public ProjectListLoader(Context context) {
        super(context);
    }

    @Override
    public void deliverResult(List<Result> data) {
        if(isReset()){
            releaseResources(data);
        }

        List<Result> oldData = projectList;
        projectList = data;

        if(isStarted()) {
            super.deliverResult(data);
        }

        if(oldData != null && oldData != data){
            releaseResources(oldData);
        }
        super.deliverResult(data);
    }
    @Override
    public void onCanceled(List<Result> data) {
        super.onCanceled(data);

        releaseResources(projectList);
    }

    @Override
    protected void onStartLoading() {
        if(projectList != null){
            deliverResult(projectList);
        }

        //TODO:register observer

        if (takeContentChanged() || projectList == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();
        if(projectList != null){
            releaseResources(projectList);
            projectList = null;
        }

        //TODO: unregister the observer
    }
    @Override
    public List<Result> loadInBackground() {
        List<Result> data;
        ProjectsListResponse projectsListResponse = null;

        SourceMeterService sourceMeter = ServiceGenerator.createService(SourceMeterService.class);
        Call<ProjectsListResponse> call = sourceMeter.listProjects();
        try {
            projectsListResponse = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = projectsListResponse.getResult();
        return data;

    }

    private void releaseResources(List<Result> data) {
    }
}
