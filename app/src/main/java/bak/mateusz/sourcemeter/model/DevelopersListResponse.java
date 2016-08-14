package bak.mateusz.sourcemeter.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DevelopersListResponse {

    @SerializedName("DeveloperItem")
    @Expose
    private List<DeveloperItem> DeveloperItem = new ArrayList<DeveloperItem>();
    @SerializedName("status")
    @Expose
    private Status status;

    /**
     *
     * @return
     * The DeveloperItem
     */
    public List<DeveloperItem> getDeveloperItem() {
        return DeveloperItem;
    }

    /**
     *
     * @param DeveloperItem
     * The DeveloperItem
     */
    public void setDeveloperItem(List<DeveloperItem> DeveloperItem) {
        this.DeveloperItem = DeveloperItem;
    }

    /**
     *
     * @return
     * The status
     */
    public Status getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

}
