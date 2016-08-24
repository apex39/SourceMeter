package bak.mateusz.sourcemeter.model;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
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

    class WeekStatistics {

        class Day {
            int elements = 0;
            double sum = 0;
        }

        Day[] weekStatistics = new Day[7];
        Long startTimestamp, endTimestamp;

        long timestamp;

        public WeekStatistics(LocalDate startTimestamp, LocalDate endTimestamp) {
            this.startTimestamp = startTimestamp.toDateTimeAtCurrentTime().getMillis();
            this.endTimestamp = endTimestamp.toDateTimeAtCurrentTime().getMillis();
            for(int i = 0; i<weekStatistics.length;i++){
                weekStatistics[i] = new Day();
            }
        }

        public List<Double> getWeekStatistics() {
            for (Map.Entry<Long, QualityTimelineEntry> entry : qualityTimeline.entrySet()) {
                Long entryTimestamp = entry.getKey();
                if (entryTimestamp >= startTimestamp && entryTimestamp <= endTimestamp) {
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
                weekStatisticsList.add(day.sum/day.elements);
            }
            return weekStatisticsList;
        }
    }

    class YearStatistics {
        class Month {
            int elements = 0;
            double sum = 0;
        }

        Month[] yearStatistics = new Month[12];
        Long startTimestamp, endTimestamp;

        long timestamp;

        public YearStatistics(LocalDate startTimestamp, LocalDate endTimestamp) {
            this.startTimestamp = startTimestamp.toDateTimeAtCurrentTime().getMillis();;
            this.endTimestamp = endTimestamp.toDateTimeAtCurrentTime().getMillis();;
            for(int i = 0; i< yearStatistics.length; i++){
                yearStatistics[i] = new Month();
            }
        }

        public List<Double> getYearStatistics() {
            for (Map.Entry<Long, QualityTimelineEntry> entry : qualityTimeline.entrySet()) {
                Long entryTimestamp = entry.getKey();
                if (entryTimestamp >= startTimestamp && entryTimestamp <= endTimestamp) {
                    LocalDate date = new LocalDate(entryTimestamp);
                    int month = date.monthOfYear().get();
                    switch (month) {
                        case DateTimeConstants.JANUARY:
                            yearStatistics[0].elements++;
                            yearStatistics[0].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.FEBRUARY:
                            yearStatistics[1].elements++;
                            yearStatistics[1].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.MARCH:
                            yearStatistics[2].elements++;
                            yearStatistics[2].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.APRIL:
                            yearStatistics[3].elements++;
                            yearStatistics[3].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.MAY:
                            yearStatistics[4].elements++;
                            yearStatistics[4].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.JUNE:
                            yearStatistics[5].elements++;
                            yearStatistics[5].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.JULY:
                            yearStatistics[6].elements++;
                            yearStatistics[6].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.AUGUST:
                            yearStatistics[7].elements++;
                            yearStatistics[7].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.SEPTEMBER:
                            yearStatistics[8].elements++;
                            yearStatistics[8].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.OCTOBER:
                            yearStatistics[9].elements++;
                            yearStatistics[9].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.NOVEMBER:
                            yearStatistics[10].elements++;
                            yearStatistics[10].sum += entry.getValue().getProjectValue();
                            break;
                        case DateTimeConstants.DECEMBER:
                            yearStatistics[11].elements++;
                            yearStatistics[11].sum += entry.getValue().getProjectValue();
                            break;
                    }
                }
            }
            List<Double> yearStatisticsList = new ArrayList<>();
            for (Month month : yearStatistics) {
                yearStatisticsList.add(month.sum/month.elements);
            }
            return yearStatisticsList;
        }
    }


    class DayStatistics {
        class Time {
            int elements = 0;
            double sum = 0;
        }

        Time[] timeStatistics = new Time[4];
        Long startTimestamp, endTimestamp;

        public DayStatistics(LocalDate startTimestamp, LocalDate endTimestamp) {
            this.startTimestamp = startTimestamp.toDateTimeAtCurrentTime().getMillis();;
            this.endTimestamp = endTimestamp.toDateTimeAtCurrentTime().getMillis();;
            for(int i = 0; i< timeStatistics.length; i++){
                timeStatistics[i] = new Time();
            }
        }

        public List<Double> getTimeStatistics() {
            for (Map.Entry<Long, QualityTimelineEntry> entry : qualityTimeline.entrySet()) {
                Long entryTimestamp = entry.getKey();
                if (entryTimestamp >= startTimestamp && entryTimestamp <= endTimestamp) {
                    LocalTime date = new LocalTime(entryTimestamp);
                    int time = date.getHourOfDay();
                    if (time >= 8 && time < 12) {
                        timeStatistics[0].elements++;
                        timeStatistics[0].sum += entry.getValue().getProjectValue();

                    } else if (time >= 12 && time < 16) {
                        timeStatistics[1].elements++;
                        timeStatistics[1].sum += entry.getValue().getProjectValue();

                    } else if (time >= 16 && time < 19) {
                        timeStatistics[2].elements++;
                        timeStatistics[2].sum += entry.getValue().getProjectValue();

                    } else if (time >= 19 || time < 8) {
                        timeStatistics[3].elements++;
                        timeStatistics[3].sum += entry.getValue().getProjectValue();

                    }
                }
            }
            List<Double> dayStatisticsList = new ArrayList<>();
            for (Time time : timeStatistics) {
                dayStatisticsList.add(time.sum/time.elements);
            }
            return dayStatisticsList;
        }
    }

}
