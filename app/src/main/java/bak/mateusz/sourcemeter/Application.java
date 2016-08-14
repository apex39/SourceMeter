package bak.mateusz.sourcemeter;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bak.mateusz.sourcemeter.model.ProjectsListResponse;
import bak.mateusz.sourcemeter.model.Project;
import bak.mateusz.sourcemeter.network.NetworkCalls;
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
        super.onCreate();
        try {
            NetworkCalls.getProjectsList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
