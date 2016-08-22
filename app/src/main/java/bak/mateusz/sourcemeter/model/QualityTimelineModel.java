package bak.mateusz.sourcemeter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by timo on 17.08.16.
 */

public class QualityTimelineModel {

    @SerializedName("result")
    @Expose
    private Map<Timestamp, QualityTimelineEntry> QualityTimeline;
    @SerializedName("status")
    @Expose
    private Status status;
}
