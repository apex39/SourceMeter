package bak.mateusz.sourcemeter.model;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016.08.22..
 */
public class ProjectQualityTimeline {
    public Map<Long, QualityTimelineEntry> qualityTimeline;

    private Status status;

    public ProjectQualityTimeline(Map<String, QualityTimelineEntry> qualityTimelineEntries) {
        qualityTimeline = new HashMap<>();
        for (Map.Entry<String, QualityTimelineEntry> entry : qualityTimelineEntries.entrySet()) {
            String key = entry.getKey();
            Long timestamp = Long.valueOf(key);
            this.qualityTimeline.put(timestamp, entry.getValue());
        }
    }

    public double getAverageQuality(String start, String end) {
        Long startTimestamp = Long.valueOf(start);
        Long endTimestamp = Long.valueOf(end);
        double sum = 0, average = 0;
        int number = 0;
        Long entryTimestamp;
        for (Map.Entry<Long, QualityTimelineEntry> entry : qualityTimeline.entrySet()) {
            entryTimestamp = entry.getKey();
            if (entryTimestamp >= startTimestamp || entryTimestamp <= endTimestamp) {
                sum = +entry.getValue().getProjectValue();
                number++;
            }
        }
        average = sum / number;

        WeekStatistics weekstatistics = new WeekStatistics(startTimestamp, endTimestamp);
        weekstatistics.getWeekStatistics();

        return average;
    }

    class WeekStatistics {

        class Day {
            int elements = 0;
            double sum = 0;
        }

        Day[] weekStatistics = new Day[7];
        Long startTimestamp, endTimestamp;

        long timestamp;

        public WeekStatistics(Long startTimestamp, Long endTimestamp) {
            this.startTimestamp = startTimestamp;
            this.endTimestamp = endTimestamp;
            for(int i = 0; i<weekStatistics.length;i++){
                weekStatistics[i] = new Day();
            }
        }

        public List<Double> getWeekStatistics() {
            for (Map.Entry<Long, QualityTimelineEntry> entry : qualityTimeline.entrySet()) {
                Long entryTimestamp = entry.getKey();
                if (entryTimestamp >= startTimestamp || entryTimestamp <= endTimestamp) {
                    LocalDate date = new LocalDate(entryTimestamp);
                    int dayOfWeek = date.dayOfWeek().get();
                    switch (dayOfWeek) {
                        case DateTimeConstants.MONDAY:
                            weekStatistics[0].elements++;
                            weekStatistics[0].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.TUESDAY:
                            weekStatistics[1].elements++;
                            weekStatistics[1].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.WEDNESDAY:
                            weekStatistics[2].elements++;
                            weekStatistics[2].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.THURSDAY:
                            weekStatistics[3].elements++;
                            weekStatistics[3].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.FRIDAY:
                            weekStatistics[4].elements++;
                            weekStatistics[4].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.SATURDAY:
                            weekStatistics[5].elements++;
                            weekStatistics[5].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.SUNDAY:
                            weekStatistics[6].elements++;
                            weekStatistics[6].sum += entry.getValue().getProjectValue();
                            break;

                    }
                }
            }
            List<Double> weekStatisticsList = new ArrayList<>();
            for (Day day : weekStatistics) {
                weekStatisticsList.add(day.sum);
            }
            return weekStatisticsList;
        }
    }

    class YearStatistics {

    }


    class DayStatistics {

    }

}
