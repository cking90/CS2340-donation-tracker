package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Category;
import edu.gatech.cs2340.nonprofitdonationtracker.models.DonationInfo;
import edu.gatech.cs2340.nonprofitdonationtracker.models.GraphType;

/**
 * Creates graphs
 */
public class GraphActivity extends AppCompatActivity {

    BarChart barChart;
    ArrayList<String> xVals;
    ArrayList<BarEntry> barEntries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Spinner graphType = (Spinner) findViewById(R.id.spinner_graphs_id);
        graphType.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, GraphType.values()));


        barChart = (BarChart) findViewById(R.id.bargraph_id);
        makeItemsByCategoryGraph(barChart);
    }

    public void makeItemsByCategoryGraph(BarChart barChart) {
        barEntries = new ArrayList<>();
        final Category categories[] = Category.values();
        float f = 0;
        for (Category category : categories) {
            float donation = DonationInfo.getNumDonationsInCategory(category);
            barEntries.add(new BarEntry(f, donation));
            f+=2;
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Number of Items");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return categories[(int)(value/2)].toString();
            }
        };

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(2f);
        xAxis.setValueFormatter(formatter);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.setDescription(null);

    }



}


