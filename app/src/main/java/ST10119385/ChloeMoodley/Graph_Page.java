package ST10119385.ChloeMoodley;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.github.mikephil.charting.charts.PieChart;

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

    }
}
