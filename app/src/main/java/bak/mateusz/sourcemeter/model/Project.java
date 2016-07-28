
package bak.mateusz.sourcemeter.model;

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bak.mateusz.sourcemeter.R;

public class Project {

    @SerializedName("projectName")
    @Expose
    private String projectName;
    @SerializedName("projectLanguage")
    @Expose
    private String projectLanguage;
    @SerializedName("qualityModel")
    @Expose
    private String qualityModel;
    @SerializedName("currentVersion")
    @Expose
    private BigInteger currentVersion;
    @SerializedName("quality")
    @Expose
    private Double quality;
    @SerializedName("qualityChange")
    @Expose
    private Double qualityChange;
    @SerializedName("threshold")
    @Expose
    private Double threshold;
    @SerializedName("uid")
    @Expose
    private Integer uid;

    /**
     * 
     * @return
     *     The projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 
     * @param projectName
     *     The projectName
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 
     * @return
     *     The projectLanguage
     */
    public String getProjectLanguage() {
        return projectLanguage;
    }

    /**
     * 
     * @param projectLanguage
     *     The projectLanguage
     */
    public void setProjectLanguage(String projectLanguage) {
        this.projectLanguage = projectLanguage;
    }

    /**
     * 
     * @return
     *     The qualityModel
     */
    public String getQualityModel() {
        return qualityModel;
    }

    /**
     * 
     * @param qualityModel
     *     The qualityModel
     */
    public void setQualityModel(String qualityModel) {
        this.qualityModel = qualityModel;
    }

    /**
     * 
     * @return
     *     The currentVersion
     */
    public BigInteger getCurrentVersion() {
        return currentVersion;
    }

    /**
     * 
     * @param currentVersion
     *     The currentVersion
     */
    public void setCurrentVersion(BigInteger currentVersion) {
        this.currentVersion = currentVersion;
    }

    /**
     * 
     * @return
     *     The quality
     */
    public Double getQuality() {
        return quality;
    }

    /**
     * 
     * @param quality
     *     The quality
     */
    public void setQuality(Double quality) {
        this.quality = quality;
    }

    /**
     * 
     * @return
     *     The qualityChange
     */
    public Double getQualityChange() {
        return qualityChange;
    }

    /**
     * 
     * @param qualityChange
     *     The qualityChange
     */
    public void setQualityChange(Double qualityChange) {
        this.qualityChange = qualityChange;
    }

    /**
     * 
     * @return
     *     The threshold
     */
    public Double getThreshold() {
        return threshold;
    }

    /**
     * 
     * @param threshold
     *     The threshold
     */
    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    /**
     * 
     * @return
     *     The uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 
     * @param uid
     *     The uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public static List<StringPair> getProjectStringDetails(Project project, Context context) {
        /*create map to get project deatils as strings */
        List<StringPair> itemList = new ArrayList<>();
        Resources res = context.getResources();
        String[] projectDetails = res.getStringArray(R.array.project_details);

        itemList.add(new StringPair(projectDetails[0],project.getProjectLanguage()));
        itemList.add(new StringPair(projectDetails[1],project.getQualityModel()));
        itemList.add(new StringPair(projectDetails[2],project.getCurrentVersion().toString()));
        itemList.add(new StringPair(projectDetails[3],project.getQuality().toString()));
        itemList.add(new StringPair(projectDetails[4],project.getQualityChange().toString()));
        itemList.add(new StringPair(projectDetails[5],project.getThreshold().toString()));

        return itemList;
    }

    public static class StringPair {
        String fristString;

        String secondString;

        public StringPair(String fristString, String secondString) {
            this.fristString = fristString;
            this.secondString = secondString;
        }

        public String getFristString() {
            return fristString;
        }

        public String getSecondString() {
            return secondString;
        }
    }
}
