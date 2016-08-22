package bak.mateusz.sourcemeter.network;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import bak.mateusz.sourcemeter.model.QualityTimelineEntry;
import bak.mateusz.sourcemeter.model.ProjectQualityTimeline;

/**
 * Created by user on 2016.08.22.
 */
public class QualityTimelineDeserializer implements JsonDeserializer<ProjectQualityTimeline> {
    @Override
    public ProjectQualityTimeline deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement content = json.getAsJsonObject().get("result");
        Map<String, QualityTimelineEntry> qualityTimelineEntries = new HashMap<>();
        Gson gson = new Gson();
        for (Map.Entry<String, JsonElement> element : content.getAsJsonObject().entrySet()) {
            String timestamp = element.getKey();
            QualityTimelineEntry qualityTimelineEntry = gson.fromJson(element.getValue(), QualityTimelineEntry.class);
            qualityTimelineEntries.put(timestamp, qualityTimelineEntry);
        }

        ProjectQualityTimeline projectQualityTimeline = new ProjectQualityTimeline(qualityTimelineEntries);
        return projectQualityTimeline;
    }
}
