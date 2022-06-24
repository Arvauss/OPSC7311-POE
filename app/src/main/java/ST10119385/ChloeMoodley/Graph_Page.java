package ST10119385.ChloeMoodley;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class Graph_Page extends AppCompatActivity {

    private static String TAG = "Graph_Page";

    private float [] yData = {2f, 3f, 5f, 9f, 5f};
    private String [] xData = {"Vegetables", "Spices", "Liquids", "Fruits", "Snacks"};
    PieChart PIE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_page);
        Log.d(TAG, "onCreate: create chart");

        PIE = (PieChart) findViewById(R.id.PieChart);

        //PIE.setDescription("DJ ");
        PIE.setRotationEnabled(true);
        PIE.setHoleRadius(15);
        PIE.setTransparentCircleAlpha(0);
        PIE.setCenterText("BODEGA");
        //PIE.setCenterText(4);
        PIE.setDrawEntryLabels(true);

        dataSet();

        PIE.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "OnValueSelected: Value select from chart.");
                Log.d(TAG, "OnValueSelected: " + e.toString());
                Log.d(TAG, "OnValueSelected: " + h.toString());

                int p1 = e.toString().indexOf("(sum): ");
                String cat = e.toString().substring(p1 + 7);

                for (int i = 0; i < yData.length; i++) {
                    if (yData[i] == Float.parseFloat(cat)) {
                        p1 = i;
                        break;
                    }
                }
                String sup = xData[p1 + 1];
                Toast.makeText(Graph_Page.this, "Category " + sup + "\n" + "Number of Items: " +"\n" +cat, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    public void dataSet () {
        Log.d(TAG, "addDataSet start");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for (int i = 0; i < xData.length; i++) {
            xEntrys.add(xData[i]);
        }

        //data list
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Supply Overview");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colour
        ArrayList<Integer> col = new ArrayList<>();
        col.add(Color.GREEN);
        col.add(Color.RED);
        col.add(Color.BLUE);
        col.add(Color.YELLOW);
        col.add(Color.GRAY);

        pieDataSet.setColors(col);

        //add legend to chart
        Legend led = PIE.getLegend();
        led.setForm(Legend.LegendForm.CIRCLE);
        //led.setPosition (Legend.LegendPosition.LEFT_OF_CHART);

        //Pie data obj
        PieData pieData = new PieData(pieDataSet);
        PIE.setData(pieData);
        PIE.invalidate();



    }

}
