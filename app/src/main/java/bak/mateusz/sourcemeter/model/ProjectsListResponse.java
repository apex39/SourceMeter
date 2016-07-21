
package bak.mateusz.sourcemeter.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectsListResponse {

    @SerializedName("result")
    @Expose
    private List<Project> project = new ArrayList<Project>();
    @SerializedName("status")
    @Expose
    private Status status;

    public static List<Project> projects;


    /**
     *
     * @return
     *     The project
     */
    public List<Project> getProject() {
        return project;
    }

    /**
     *
     * @param project
     *     The project
     */
    public void setProject(List<Project> project) {
        this.project = project;
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
