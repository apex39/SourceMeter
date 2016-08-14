package bak.mateusz.sourcemeter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DeveloperItem {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("commits")
    @Expose
    private Integer commits;
    @SerializedName("qualityChange")
    @Expose
    private Double qualityChange;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The commits
     */
    public Integer getCommits() {
        return commits;
    }

    /**
     *
     * @param commits
     * The commits
     */
    public void setCommits(Integer commits) {
        this.commits = commits;
    }

    /**
     *
     * @return
     * The qualityChange
     */
    public Double getQualityChange() {
        return qualityChange;
    }

    /**
     *
     * @param qualityChange
     * The qualityChange
     */
    public void setQualityChange(Double qualityChange) {
        this.qualityChange = qualityChange;
    }

}