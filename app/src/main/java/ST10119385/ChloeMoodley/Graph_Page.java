package ST10119385.ChloeMoodley;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.test.Dashboard_Activity;
import com.example.test.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Graph_Page extends AppCompatActivity {

    private static String TAG = "Graph_Page";

    private float [] yData = {2f, 3f, 5f, 9f, 5f};
    private String [] xData = {"Vegetables", "Spices", "Liquids", "Fruits", "Snacks"};
    PieChart PIE;

    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView burgerNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_page);

        // drawer layout instance to toggle the menu icon to open
        //drawer and back button to close drawer (geeksforgeeks.org, 2022).
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        //Instantiating burgerNavigationView and binding it to view (Pulak, 2017).
        burgerNavigationView = findViewById(R.id.burgerNavigationView);

        //Setting navigation item listener (Pulak, 2017).
        burgerNavigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button (geeksforgeeks.org, 2022).
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar (geeksforgeeks.org, 2022).
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

                int p1 = 0;
                int yVal = (int) h.getY();
                int xVal = (int) h.getX();

                //String cat = e.toString().substring(p1 + 11, 14);
              //  Log.d(TAG, "OnValueSelected: p1= " + p1);
               // Log.d(TAG, "OnValueSelected: cat= " + cat);


                String sup = xData[xVal];
                int numItems = yVal;
                Toast.makeText(Graph_Page.this, "Category " + sup + "\n" + "Number of Items: " +"\n" +numItems, Toast.LENGTH_LONG).show();
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

    //Method to handle the OnCLicked events within the burger menu (Pulak, 2017)
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            //Go to dashboard
            Intent dashB = new Intent(this, Dashboard_Activity.class);
            startActivity(dashB);
        } else if (id == R.id.nav_shopping_list){
            //Go to shopping list page
            Intent shopList = new Intent(this, ShoppingList_Page.class);
            startActivity(shopList);
        } else if (id == R.id.nav_graph_screen) {
            //Go to Graph page
            Intent graphPage = new Intent(this, Graph_Page.class);
            startActivity(graphPage);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked (geeksforgeeks.org, 2022).
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
