
package bak.mateusz.sourcemeter.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import bak.mateusz.sourcemeter.model.Status;

public class ProjectsListResponse {

    @SerializedName("result")
    @Expose
    private List<Result> result = new ArrayList<Result>();
    @SerializedName("status")
    @Expose
    private Status status;

    public static List<Result> results;

    static Map<Integer, Result> ITEM_MAP = new HashMap<Integer, Result>();

    public static void createMap() {

        //create map to get project deatils easily by fragment using uid
        for (Result project : results) {
            ITEM_MAP.put(project.getUid(),project);
        }
    }

    public static Result getProject(int anInt) {
        return ITEM_MAP.get(anInt);
    }

    /**
     *
     * @return
     *     The result
     */
    public List<Result> getResult() {
        return result;
    }

    /**
     *
     * @param result
     *     The result
     */
    public void setResult(List<Result> result) {
        this.result = result;
    }

    /**
     *
     * @return
     *     The status
     */
    public Status getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *     The status
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
