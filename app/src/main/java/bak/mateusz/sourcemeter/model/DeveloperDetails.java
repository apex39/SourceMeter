package bak.mateusz.sourcemeter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeveloperDetails {

    @SerializedName("committer")
    @Expose
    private String committer;
    @SerializedName("commits")
    @Expose
    private Integer commits;
    @SerializedName("qualityChange")
    @Expose
    private Double qualityChange;
    @SerializedName("projectManagementStatistics")
    @Expose
    private ProjectManagementStatistics projectManagementStatistics;
    @SerializedName("addedLines")
    @Expose
    private Integer addedLines;
    @SerializedName("modifiedLines")
    @Expose
    private Integer modifiedLines;
    @SerializedName("removedLines")
    @Expose
    private Integer removedLines;
    @SerializedName("ruleViolationsCreated")
    @Expose
    private Integer ruleViolationsCreated;
    @SerializedName("ruleViolationsRemoved")
    @Expose
    private Integer ruleViolationsRemoved;
    @SerializedName("appearingCCSmells")
    @Expose
    private Integer appearingCCSmells;
    @SerializedName("disappearingCCSmells")
    @Expose
    private Integer disappearingCCSmells;
    @SerializedName("appearingCISmells")
    @Expose
    private Integer appearingCISmells;
    @SerializedName("disappearingCISmells")
    @Expose
    private Integer disappearingCISmells;
    @SerializedName("movingCISmells")
    @Expose
    private Integer movingCISmells;
    @SerializedName("avgAddedLines")
    @Expose
    private Integer avgAddedLines;
    @SerializedName("avgModifiedLines")
    @Expose
    private Integer avgModifiedLines;
    @SerializedName("avgRemovedLines")
    @Expose
    private Integer avgRemovedLines;
    @SerializedName("avgRuleViolationsCreated")
    @Expose
    private Integer avgRuleViolationsCreated;
    @SerializedName("avgRuleViolationsRemoved")
    @Expose
    private Integer avgRuleViolationsRemoved;
    @SerializedName("avgAppearingCCSmells")
    @Expose
    private Integer avgAppearingCCSmells;
    @SerializedName("avgDisappearingCCSmells")
    @Expose
    private Integer avgDisappearingCCSmells;
    @SerializedName("avgAppearingCISmells")
    @Expose
    private Integer avgAppearingCISmells;
    @SerializedName("avgDisappearingCISmells")
    @Expose
    private Integer avgDisappearingCISmells;
    @SerializedName("avgMovingCISmells")
    @Expose
    private Integer avgMovingCISmells;

    /**
     *
     * @return
     * The committer
     */
    public String getCommitter() {
        return committer;
    }

    /**
     *
     * @param committer
     * The committer
     */
    public void setCommitter(String committer) {
        this.committer = committer;
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

    /**
     *
     * @return
     * The projectManagementStatistics
     */
    public ProjectManagementStatistics getProjectManagementStatistics() {
        return projectManagementStatistics;
    }

    /**
     *
     * @param projectManagementStatistics
     * The projectManagementStatistics
     */
    public void setProjectManagementStatistics(ProjectManagementStatistics projectManagementStatistics) {
        this.projectManagementStatistics = projectManagementStatistics;
    }

    /**
     *
     * @return
     * The addedLines
     */
    public Integer getAddedLines() {
        return addedLines;
    }

    /**
     *
     * @param addedLines
     * The addedLines
     */
    public void setAddedLines(Integer addedLines) {
        this.addedLines = addedLines;
    }

    /**
     *
     * @return
     * The modifiedLines
     */
    public Integer getModifiedLines() {
        return modifiedLines;
    }

    /**
     *
     * @param modifiedLines
     * The modifiedLines
     */
    public void setModifiedLines(Integer modifiedLines) {
        this.modifiedLines = modifiedLines;
    }

    /**
     *
     * @return
     * The removedLines
     */
    public Integer getRemovedLines() {
        return removedLines;
    }

    /**
     *
     * @param removedLines
     * The removedLines
     */
    public void setRemovedLines(Integer removedLines) {
        this.removedLines = removedLines;
    }

    /**
     *
     * @return
     * The ruleViolationsCreated
     */
    public Integer getRuleViolationsCreated() {
        return ruleViolationsCreated;
    }

    /**
     *
     * @param ruleViolationsCreated
     * The ruleViolationsCreated
     */
    public void setRuleViolationsCreated(Integer ruleViolationsCreated) {
        this.ruleViolationsCreated = ruleViolationsCreated;
    }

    /**
     *
     * @return
     * The ruleViolationsRemoved
     */
    public Integer getRuleViolationsRemoved() {
        return ruleViolationsRemoved;
    }

    /**
     *
     * @param ruleViolationsRemoved
     * The ruleViolationsRemoved
     */
    public void setRuleViolationsRemoved(Integer ruleViolationsRemoved) {
        this.ruleViolationsRemoved = ruleViolationsRemoved;
    }

    /**
     *
     * @return
     * The appearingCCSmells
     */
    public Integer getAppearingCCSmells() {
        return appearingCCSmells;
    }

    /**
     *
     * @param appearingCCSmells
     * The appearingCCSmells
     */
    public void setAppearingCCSmells(Integer appearingCCSmells) {
        this.appearingCCSmells = appearingCCSmells;
    }

    /**
     *
     * @return
     * The disappearingCCSmells
     */
    public Integer getDisappearingCCSmells() {
        return disappearingCCSmells;
    }

    /**
     *
     * @param disappearingCCSmells
     * The disappearingCCSmells
     */
    public void setDisappearingCCSmells(Integer disappearingCCSmells) {
        this.disappearingCCSmells = disappearingCCSmells;
    }

    /**
     *
     * @return
     * The appearingCISmells
     */
    public Integer getAppearingCISmells() {
        return appearingCISmells;
    }

    /**
     *
     * @param appearingCISmells
     * The appearingCISmells
     */
    public void setAppearingCISmells(Integer appearingCISmells) {
        this.appearingCISmells = appearingCISmells;
    }

    /**
     *
     * @return
     * The disappearingCISmells
     */
    public Integer getDisappearingCISmells() {
        return disappearingCISmells;
    }

    /**
     *
     * @param disappearingCISmells
     * The disappearingCISmells
     */
    public void setDisappearingCISmells(Integer disappearingCISmells) {
        this.disappearingCISmells = disappearingCISmells;
    }

    /**
     *
     * @return
     * The movingCISmells
     */
    public Integer getMovingCISmells() {
        return movingCISmells;
    }

    /**
     *
     * @param movingCISmells
     * The movingCISmells
     */
    public void setMovingCISmells(Integer movingCISmells) {
        this.movingCISmells = movingCISmells;
    }

    /**
     *
     * @return
     * The avgAddedLines
     */
    public Integer getAvgAddedLines() {
        return avgAddedLines;
    }

    /**
     *
     * @param avgAddedLines
     * The avgAddedLines
     */
    public void setAvgAddedLines(Integer avgAddedLines) {
        this.avgAddedLines = avgAddedLines;
    }

    /**
     *
     * @return
     * The avgModifiedLines
     */
    public Integer getAvgModifiedLines() {
        return avgModifiedLines;
    }

    /**
     *
     * @param avgModifiedLines
     * The avgModifiedLines
     */
    public void setAvgModifiedLines(Integer avgModifiedLines) {
        this.avgModifiedLines = avgModifiedLines;
    }

    /**
     *
     * @return
     * The avgRemovedLines
     */
    public Integer getAvgRemovedLines() {
        return avgRemovedLines;
    }

    /**
     *
     * @param avgRemovedLines
     * The avgRemovedLines
     */
    public void setAvgRemovedLines(Integer avgRemovedLines) {
        this.avgRemovedLines = avgRemovedLines;
    }

    /**
     *
     * @return
     * The avgRuleViolationsCreated
     */
    public Integer getAvgRuleViolationsCreated() {
        return avgRuleViolationsCreated;
    }

    /**
     *
     * @param avgRuleViolationsCreated
     * The avgRuleViolationsCreated
     */
    public void setAvgRuleViolationsCreated(Integer avgRuleViolationsCreated) {
        this.avgRuleViolationsCreated = avgRuleViolationsCreated;
    }

    /**
     *
     * @return
     * The avgRuleViolationsRemoved
     */
    public Integer getAvgRuleViolationsRemoved() {
        return avgRuleViolationsRemoved;
    }

    /**
     *
     * @param avgRuleViolationsRemoved
     * The avgRuleViolationsRemoved
     */
    public void setAvgRuleViolationsRemoved(Integer avgRuleViolationsRemoved) {
        this.avgRuleViolationsRemoved = avgRuleViolationsRemoved;
    }

    /**
     *
     * @return
     * The avgAppearingCCSmells
     */
    public Integer getAvgAppearingCCSmells() {
        return avgAppearingCCSmells;
    }

    /**
     *
     * @param avgAppearingCCSmells
     * The avgAppearingCCSmells
     */
    public void setAvgAppearingCCSmells(Integer avgAppearingCCSmells) {
        this.avgAppearingCCSmells = avgAppearingCCSmells;
    }

    /**
     *
     * @return
     * The avgDisappearingCCSmells
     */
    public Integer getAvgDisappearingCCSmells() {
        return avgDisappearingCCSmells;
    }

    /**
     *
     * @param avgDisappearingCCSmells
     * The avgDisappearingCCSmells
     */
    public void setAvgDisappearingCCSmells(Integer avgDisappearingCCSmells) {
        this.avgDisappearingCCSmells = avgDisappearingCCSmells;
    }

    /**
     *
     * @return
     * The avgAppearingCISmells
     */
    public Integer getAvgAppearingCISmells() {
        return avgAppearingCISmells;
    }

    /**
     *
     * @param avgAppearingCISmells
     * The avgAppearingCISmells
     */
    public void setAvgAppearingCISmells(Integer avgAppearingCISmells) {
        this.avgAppearingCISmells = avgAppearingCISmells;
    }

    /**
     *
     * @return
     * The avgDisappearingCISmells
     */
    public Integer getAvgDisappearingCISmells() {
        return avgDisappearingCISmells;
    }

    /**
     *
     * @param avgDisappearingCISmells
     * The avgDisappearingCISmells
     */
    public void setAvgDisappearingCISmells(Integer avgDisappearingCISmells) {
        this.avgDisappearingCISmells = avgDisappearingCISmells;
    }

    /**
     *
     * @return
     * The avgMovingCISmells
     */
    public Integer getAvgMovingCISmells() {
        return avgMovingCISmells;
    }

    /**
     *
     * @param avgMovingCISmells
     * The avgMovingCISmells
     */
    public void setAvgMovingCISmells(Integer avgMovingCISmells) {
        this.avgMovingCISmells = avgMovingCISmells;
    }

}
