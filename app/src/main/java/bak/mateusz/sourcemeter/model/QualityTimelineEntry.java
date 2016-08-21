package bak.mateusz.sourcemeter.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by timo on 17.08.16.
 */
public class QualityTimelineEntry extends TimelineEntry{
    @SerializedName("value")
    @Expose
    double value;

}
