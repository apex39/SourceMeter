package bak.mateusz.sourcemeter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProjectManagementStatistics {

    @SerializedName("averageLinesPerDay")
    @Expose
    private Double averageLinesPerDay;
    @SerializedName("globalAverageLinesPerDay")
    @Expose
    private Double globalAverageLinesPerDay;

    /**
     *
     * @return
     * The averageLinesPerDay
     */
    public Double getAverageLinesPerDay() {
        return averageLinesPerDay;
    }

    /**
     *
     * @param averageLinesPerDay
     * The averageLinesPerDay
     */
    public void setAverageLinesPerDay(Double averageLinesPerDay) {
        this.averageLinesPerDay = averageLinesPerDay;
    }

    /**
     *
     * @return
     * The globalAverageLinesPerDay
     */
    public Double getGlobalAverageLinesPerDay() {
        return globalAverageLinesPerDay;
    }

    /**
     *
     * @param globalAverageLinesPerDay
     * The globalAverageLinesPerDay
     */
    public void setGlobalAverageLinesPerDay(Double globalAverageLinesPerDay) {
        this.globalAverageLinesPerDay = globalAverageLinesPerDay;
    }

}
