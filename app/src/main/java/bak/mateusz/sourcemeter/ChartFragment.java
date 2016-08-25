package bak.mateusz.sourcemeter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by user on 2016.08.24..
 */
public class ChartFragment extends Fragment {
    View rootView;
    List<Double> statistics;
    List<BarEntry> entries;
    BarChart chart;

    public ChartFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.chart, container, false);
        chart = (BarChart) rootView.findViewById(R.id.chart);
        statistics = (List<Double>) getArguments().getSerializable("statistics");
        chart.setDrawValueAboveBar(true);
        chart.animateXY(1500, 1500);
        chart.setFitBars(true);



        final String[] months = getResources().getStringArray(R.array.months);

        AxisValueFormatter formatter = new AxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return months[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed
            @Override
            public int getDecimalDigits() {  return 0; }
        };
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        xAxis.setLabelCount(7);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(8, false);

        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);

        rightAxis.setSpaceTop(15f);
        entries = new ArrayList<BarEntry>();

        addChartData();

        return rootView;
        
    }

    private void addChartData() {


        for (int i = 0; i < 12;i++) {
            entries.add(new BarEntry((float)i, statistics.get(i).floatValue() ));
        }
        BarDataSet dataSet = new BarDataSet(entries,"Year");
        BarData barData = new BarData(dataSet);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        barData.setBarWidth(0.7f);
        chart.setData(barData);
        chart.invalidate();
    }
}
