package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Category;
import edu.gatech.cs2340.nonprofitdonationtracker.models.DonationInfo;
import edu.gatech.cs2340.nonprofitdonationtracker.models.GraphType;
import edu.gatech.cs2340.nonprofitdonationtracker.models.Location;

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
        makeItemsByCategoryGraph();
    }

    /**
     * the on click method for a button which displays graph
     * @param view - view
     */
    public void onClickDisplay (View view) {
        Spinner graphType = (Spinner) findViewById(R.id.spinner_graphs_id);
        GraphType graph = (GraphType) graphType.getSelectedItem();

        if (graph.equals(GraphType.ITEM_BY_CATEGORY)) {
            barChart.invalidate();
            makeItemsByCategoryGraph();
        } else if (graph.equals(GraphType.INVENTORY_VALUE_BY_MONTH)) {
            barChart.invalidate();
            makeInventoryValueByMonthGraph();
        } else if (graph.equals(GraphType.INCOME_PER_MONTH)) {
            barChart.invalidate();
            makeIncomePerMonthGraph();
        } else if (graph.equals(GraphType.DONATIONS_PER_MONTH_PER_LOCATION)) {
            barChart.invalidate();
            makeDonationsPerMonthPerLocationGraph();
        }
    }

    private void makeDonationsPerMonthPerLocationGraph() {
        HashMap<Location, Float> rates = DonationInfo.getDonationsPerMonthPerLocation();
        barEntries = new ArrayList<>();
        Set<Location> locs = Location.getLocationList();

        HashMap<Location, Float> donationRates = DonationInfo.getDonationsPerMonthPerLocation();
        final String locations[] = new String[locs.size()];


        int i = 0;
        for (Location loc : locs) {
            locations[i] = (loc.toString().substring(0, 8) + "...");
            float value = donationRates.get(loc);
            barEntries.add(new BarEntry((float)i, value));
            i++;
        }


        BarDataSet barDataSet = new BarDataSet(barEntries, "Donation rate per month");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);


        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value < locations.length) {
                    return locations[(int) value];
                }
                return null;
            }
        };

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);

        xAxis.setValueFormatter(formatter);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.setDescription(null);
    }

    private void makeIncomePerMonthGraph() {
        barEntries = new ArrayList<>();
        final String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        float[] incomesPerMonth = DonationInfo.getIncomePerMonth();
        float f = 0;
        for (float income : incomesPerMonth) {
            barEntries.add(new BarEntry(f, income));
            f++;
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Income");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return months[(int)value];
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

    private void makeInventoryValueByMonthGraph() {
        barEntries = new ArrayList<>();
        final String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        float[] inventoryVals = DonationInfo.getValueOfInventoryPerMonth();
        float f = 0;
        for (float totalVal: inventoryVals) {
            barEntries.add(new BarEntry(f, totalVal));
            f++;
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Inventory Value");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return months[(int)value];
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

    public void makeItemsByCategoryGraph() {
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


