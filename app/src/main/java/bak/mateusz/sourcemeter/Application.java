package bak.mateusz.sourcemeter;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bak.mateusz.sourcemeter.model.ProjectsListResponse;
import bak.mateusz.sourcemeter.model.Result;
import bak.mateusz.sourcemeter.network.ServiceGenerator;
import bak.mateusz.sourcemeter.network.SourceMeterService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 2016.07.18..
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        try {
            getProjectsList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onCreate();
    }

    private void getProjectsList() throws IOException {
        SourceMeterService sourceMeter = ServiceGenerator.createService(SourceMeterService.class);
        Call<ProjectsListResponse> call = sourceMeter.listProjects();
        call.enqueue(new Callback<ProjectsListResponse>() {
            @Override
            public void onResponse(Call<ProjectsListResponse> call, Response<ProjectsListResponse> response) {
                List<Result> projectList;
                projectList = response.body().getResult();
                EventBus.getDefault().postSticky(projectList);

                Map<Integer, Result> projectsMap = createMap(projectList);
                EventBus.getDefault().postSticky(projectsMap);
            }

            @Override
            public void onFailure(Call<ProjectsListResponse> call, Throwable t) {
                //TODO: Failure toast message
            }
        });
    }

    private Map<Integer, Result> createMap(List<Result> results) {
        /*create map to get project deatils easily by fragment using uid*/
        Map<Integer, Result> itemMap = new HashMap<Integer, Result>();

        for (Result project : results) {
            itemMap.put(project.getUid(),project);
        }
        return itemMap;
    }

}
