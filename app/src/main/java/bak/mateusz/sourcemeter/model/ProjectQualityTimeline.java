package bak.mateusz.sourcemeter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by user on 2016.08.22..
 */
public class ProjectQualityTimeline {
    public Map<String, QualityTimelineEntry> QualityTimeline;

    private Status status;

    public ProjectQualityTimeline(Map<String, QualityTimelineEntry> qualityTimelineEntries) {
        this.QualityTimeline = qualityTimelineEntries;
    }
}
