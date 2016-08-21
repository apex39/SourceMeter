package bak.mateusz.sourcemeter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timo on 17.08.16.
 */

public class QualityTimelineModel {

    @SerializedName("result")
    @Expose
    private List<QualityTimelineEntry> QualityTimeline = new ArrayList<QualityTimelineEntry>();
    @SerializedName("status")
    @Expose
    private Status status;
}
