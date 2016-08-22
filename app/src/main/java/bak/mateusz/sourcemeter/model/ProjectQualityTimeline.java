package bak.mateusz.sourcemeter.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016.08.22..
 */
public class ProjectQualityTimeline {
    public Map<Long, QualityTimelineEntry> qualityTimeline;

    private Status status;

    public ProjectQualityTimeline(Map<String, QualityTimelineEntry> qualityTimelineEntries) {
        qualityTimeline = new HashMap<>();
        for (Map.Entry<String,QualityTimelineEntry> entry : qualityTimelineEntries.entrySet()) {
            String key = entry.getKey();
            Long timestamp = Long.valueOf(key);
            this.qualityTimeline.put(timestamp,entry.getValue());
        }
    }

    public double getAverageQuality(String start, String end){
        Long startTimestamp = Long.valueOf(start);
        Long endTimestamp = Long.valueOf(end);
        double sum = 0, average = 0;
        int number = 0;
        Long entryTimestamp;
        for (Map.Entry<Long,QualityTimelineEntry> entry : qualityTimeline.entrySet()) {
            entryTimestamp = entry.getKey();
            if(entryTimestamp >= startTimestamp || entryTimestamp <= endTimestamp){
                sum =+ entry.getValue().getValue();
                number++;
            }
        }
        average = sum/number;
        return average;
    }
}
