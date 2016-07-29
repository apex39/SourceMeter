package bak.mateusz.sourcemeter.network;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bak.mateusz.sourcemeter.model.Project;
import bak.mateusz.sourcemeter.model.ProjectsListResponse;
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

}
