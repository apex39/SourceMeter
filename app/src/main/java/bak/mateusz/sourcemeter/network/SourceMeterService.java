package bak.mateusz.sourcemeter.network;

import bak.mateusz.sourcemeter.model.DeveloperDetails;
import bak.mateusz.sourcemeter.model.DevelopersListResponse;
import bak.mateusz.sourcemeter.model.ProjectsListResponse;
import bak.mateusz.sourcemeter.model.QualityTimelineModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by user on 2016.07.12..
 */
public interface SourceMeterService {
    @GET("projects")
    Call<ProjectsListResponse> listProjects();

    @GET("projects/{uid}/developers")
    Call<DevelopersListResponse> getDevelopers(@Path("uid") long uid);

    @GET("projects/{uid}/qualitytimeline?limit=999999")
    Call<QualityTimelineModel> getQualityTimeLine(@Path("uid") long uid);
}
