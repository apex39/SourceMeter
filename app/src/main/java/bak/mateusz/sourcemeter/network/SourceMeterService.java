package bak.mateusz.sourcemeter.network;

import java.util.List;

import bak.mateusz.sourcemeter.model.ProjectModel;
import bak.mateusz.sourcemeter.model.ProjectsListResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 2016.07.12..
 */
public interface SourceMeterService {
    @GET("projects")
    Call<ProjectsListResponse> listProjects();
}
