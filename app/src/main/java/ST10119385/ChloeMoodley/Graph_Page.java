package ST10119385.ChloeMoodley;


import android.content.ClipData;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class Graph_Page extends AppCompatActivity {

    private static String TAG = "Graph_Page";

    private static float [] yData;
    private static String [] xData;
    PieChart PIE;
    float ItemsTotal = 0;

    ArrayList<Item_Information> ItemList = new ArrayList<>();
    ArrayList<Category_Information> catList = new ArrayList<>();
    DatabaseReference dbRef;
    private FirebaseAuth Auth;
    FirebaseUser user;

    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView burgerNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_page);

        dbRef = FirebaseDatabase.getInstance("https://bodegaapp-opscpoe-default-rtdb.firebaseio.com/").getReference();
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();

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

        InitListData();


    }

    private void InitListData() {
        catList.clear();
        DatabaseReference catRef = dbRef.child("categories");


        //stores all user categories into arraylist
        catRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Category_Information catObj;
                for(DataSnapshot ds : task.getResult().getChildren()){
                    if (Objects.equals(ds.child("uid").getValue(String.class), user.getUid())){
                        catObj = ds.getValue(Category_Information.class);
                        catList.add(catObj);
                    }
                }
                PopulateItemList();
            }
        });
    }

    private void PopulateItemList() {
        // pulling data from DB into ArrayList for ListView
        ItemList.clear();

        DatabaseReference itmRef = dbRef.child("items");
        //gets snapshot of all items
        itmRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Item_Information item;
                //adds every category object in DB to catList
                for(DataSnapshot ds : task.getResult().getChildren()){
                    for(Category_Information cat: catList){
                        if(ds.child("cat_ID").getValue(String.class).equals(cat.getCatID())){
                            item = ds.getValue(Item_Information.class);
                            ItemList.add(item);
                        }
                    }
                }
                InitPieChart();
            }
        });
    }

    private void InitPieChart() {
        PIE = (PieChart) findViewById(R.id.PieChart);

        PIE.setRotationEnabled(true);
        PIE.setHoleRadius(15);
        PIE.setTransparentCircleAlpha(0);
        PIE.setCenterText("BODEGA");

        PIE.setDrawEntryLabels(true);

        InitPieData();
        dataSet();

        PIE.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "OnValueSelected: Value select from chart.");
                Log.d(TAG, "OnValueSelected: " + e.toString());
                Log.d(TAG, "OnValueSelected: " + h.toString());

                int p1 = 0;
                float yVal = h.getY();
                int xVal = (int) h.getX();

                String sup = catList.get(xVal).getCategory_Name();
                float numItems = yVal;
                Toast.makeText(Graph_Page.this, "Category " + sup + "\n" + "Percentage of Items: " +"\n" +numItems, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    public void InitPieData(){


        //yData = ;
        //xData = ;
    }

    public void dataSet () {
        Log.d(TAG, "addDataSet start");
        ArrayList<Integer> tempCatTotals = new ArrayList<Integer>();
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        int catPos = 0;
        //Initialises dataset for xEntry
        for(Category_Information cat: catList){

            xEntrys.add(cat.getCategory_Name());
            int catTotalItems = 0;
            //calculates total num of items per category
            for(Item_Information item: ItemList){
                if (item.getCat_ID().equals(cat.getCatID())){
                    catTotalItems += item.getQty();
                }

            }
            tempCatTotals.add(catTotalItems);
            ItemsTotal += catTotalItems;
        }

        //adds percentage of items for each category to yEntrys
        for (int i = 0; i < tempCatTotals.size(); i++) {
            float percentage = (tempCatTotals.get(i)/ItemsTotal) * 100;

            yEntrys.add(new PieEntry(percentage, i));

        }

        //data list
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Supply Overview");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colour
        ArrayList<Integer> col = new ArrayList<>();
        for(Category_Information cat: catList){
            col.add(cat.getCategory_Colour());
        }
        /*col.add(Color.GREEN);
        col.add(Color.RED);
        col.add(Color.BLUE);
        col.add(Color.YELLOW);
        col.add(Color.GRAY);*/

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
