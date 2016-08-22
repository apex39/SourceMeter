package bak.mateusz.sourcemeter.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by timo on 17.08.16.
 */
public class QualityTimelineEntry {
    @SerializedName("value")
    @Expose
    double value;
    @SerializedName("revision")
    @Expose
    String revision;
    @SerializedName("committer")
    @Expose
    String committer;

    public double getValue() {
        return value;
    }

    public String getRevision() {
        return revision;
    }

    public String getCommitter() {
        return committer;
    }
}
