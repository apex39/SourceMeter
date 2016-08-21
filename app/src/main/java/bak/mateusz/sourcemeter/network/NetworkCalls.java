package bak.mateusz.sourcemeter.network;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bak.mateusz.sourcemeter.model.DeveloperDetails;
import bak.mateusz.sourcemeter.model.DeveloperItem;
import bak.mateusz.sourcemeter.model.DevelopersListResponse;
import bak.mateusz.sourcemeter.model.Project;
import bak.mateusz.sourcemeter.model.ProjectsListResponse;
import bak.mateusz.sourcemeter.model.QualityTimelineModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 2016.07.29..
 */
public class NetworkCalls {
    public static void getProjectsList() throws IOException {
        SourceMeterService sourceMeter = ServiceGenerator.createService(SourceMeterService.class);
        Call<ProjectsListResponse> call = sourceMeter.listProjects();
        call.enqueue(new Callback<ProjectsListResponse>() {
            @Override
            public void onResponse(Call<ProjectsListResponse> call, Response<ProjectsListResponse> response) {
                List<Project> projectList;
                projectList = response.body().getProject();
                Collections.sort(projectList, new Comparator<Project>() {
                    @Override
                    public int compare(Project project, Project t1) {
                        return project.getProjectName().compareTo(t1.getProjectName());
                    }
                });
                EventBus.getDefault().postSticky(projectList);

                Map<Integer, Project> projectsMap = createProjectsListMap(projectList);
                EventBus.getDefault().postSticky(projectsMap);
            }

            @Override
            public void onFailure(Call<ProjectsListResponse> call, Throwable t) {
                EventBus.getDefault().post(t);
            }
        });
    }

    private static Map<Integer, Project> createProjectsListMap(List<Project> projects) {
        /*create map to get project deatils easily by fragment using uid*/
        Map<Integer, Project> itemMap = new HashMap<Integer, Project>();

        for (Project project : projects) {
            itemMap.put(project.getUid(),project);
        }
        return itemMap;
    }
    public static void getDevelopersList(int projectUID) throws IOException {
        SourceMeterService sourceMeter = ServiceGenerator.createService(SourceMeterService.class);
        Call<DevelopersListResponse> call = sourceMeter.getDevelopers(projectUID);
        call.enqueue(new Callback<DevelopersListResponse>() {
            @Override
            public void onResponse(Call<DevelopersListResponse> call, Response<DevelopersListResponse> response) {
                List<DeveloperItem> developersList;
                developersList = response.body().getDeveloperItem();
                Collections.sort(developersList, new Comparator<DeveloperItem>() {
                    @Override
                    public int compare(DeveloperItem developer, DeveloperItem t1) {
                        return t1.getCommits().compareTo(developer.getCommits());
                    }
                });
                EventBus.getDefault().post(developersList);
            }

            @Override
            public void onFailure(Call<DevelopersListResponse> call, Throwable t) {
                EventBus.getDefault().post(t);
            }
        });
    }
    public static void getQualityTimeline(int projectUID) throws IOException {
        SourceMeterService sourceMeter = ServiceGenerator.createService(SourceMeterService.class);
        Call<QualityTimelineModel> call = sourceMeter.getQualityTimeLine(projectUID);
        call.enqueue(new Callback<QualityTimelineModel>() {
            @Override
            public void onResponse(Call<QualityTimelineModel> call, Response<QualityTimelineModel> response) {
                QualityTimelineModel qualityTimelineModel;
                qualityTimelineModel = response.body();

                EventBus.getDefault().post(qualityTimelineModel);
            }

            @Override
            public void onFailure(Call<QualityTimelineModel> call, Throwable t) {
                EventBus.getDefault().post(t);
            }
        });
    }

}
