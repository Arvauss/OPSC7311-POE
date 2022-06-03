package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import ST10119385.ChloeMoodley.Category_Information;
import ST10119385.ChloeMoodley.Category_Page;
import ST10119385.ChloeMoodley.ShoppingList_Page;
import ST10119385.ChloeMoodley.item_list_all;
import opscwork.viewitempagefeatures.ItemPage;

public class Dashboard_Activity extends AppCompatActivity {
    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView burgerNavigationView;

    Button btnAddCategory, btnViewAllItems;
    ListView CatListView;
    public static ArrayList<Category_Information> catList = new ArrayList<Category_Information>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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


        setupUI();
        //method to populate ArrayList with demo data
        if (catList.size() < 3)
            InitListData();
        //Setups listview and connects adapter
        SetupListView();
        setupOnclickListeners();

    }

    private void SetupListView() {
        CatListView = (ListView) findViewById(R.id.rv_category_cardlist);

        DashAdapter adapter = new DashAdapter(getApplicationContext(), 0, catList);
        CatListView.setAdapter(adapter);
    }

    private void InitListData() {
        Category_Information Vegetables = new Category_Information(Color.parseColor("#41FC00"), "Vegetables", "Vegetables");
        catList.add(Vegetables);
        Category_Information Fruits = new Category_Information(Color.parseColor("#FC8A00"), "Fruits", "Fruits");
        catList.add(Fruits);
        Category_Information Wines = new Category_Information(Color.parseColor("#FF0074"), "Wines", "Wines");
        catList.add(Wines);
    }

    private void setupUI() {
        btnAddCategory = (Button) findViewById(R.id.DashboardAddButton);
      //  btnViewAllItems = (Button) findViewById(R.id.DashboardViewAllButton);
        btnViewAllItems = (Button) findViewById(R.id.DashboardDeleteButton);
    }

    public void setupOnclickListeners(){
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCategory(view);
            }
        });


        /*btnViewAllItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        CatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Category_Information catObj = (Category_Information) (CatListView.getItemAtPosition(position));
                Intent displayCatItems = new Intent(Dashboard_Activity.this, ItemPage.class);
                displayCatItems.putExtra("id", position);
                startActivity(displayCatItems);
            }
        });

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
            //   Intent graphPage = new Intent(this, ShoppingList_Page.class);
            //   startActivity(graphPage);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void AddCategory (View v) {
        Intent addCat = new Intent(this, Category_Page.class);
        startActivity(addCat);
    }

    public void GoToViewAllItem (View v) {
        Intent viewItemAll = new Intent(this, item_list_all.class);
        startActivity(viewItemAll);
    }

}