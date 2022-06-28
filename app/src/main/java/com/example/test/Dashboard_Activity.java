package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import ST10119385.ChloeMoodley.Category_Information;
import ST10119385.ChloeMoodley.Category_Page;
import ST10119385.ChloeMoodley.Graph_Page;
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
    DatabaseReference dbRef;
    private FirebaseAuth Auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dbRef = FirebaseDatabase.getInstance("https://bodegaapp-opscpoe-default-rtdb.firebaseio.com/").getReference();
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();

        // drawer layout instance to toggle the menu icon to open (The IIE, 2022)
        //drawer and back button to close drawer (geeksforgeeks.org, 2022).
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        //Instantiating burgerNavigationView and binding it to view (Pulak, 2017).
        burgerNavigationView = findViewById(R.id.burgerNavigationView);

        //Setting navigation item listener (Pulak, 2017).
        burgerNavigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        // pass the Open and Close toggle for the drawer layout listener (The IIE, 2022)
        // to toggle the button (geeksforgeeks.org, 2022).
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar (geeksforgeeks.org, 2022).
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setupUI();
        InitListData(dbRef);

    }

    // The method below populates the list view for the category page (The IIE, 2022)
    private void SetupListView() {
        CatListView = (ListView) findViewById(R.id.rv_category_cardlist);

        DashAdapter adapter = new DashAdapter(getApplicationContext(), 0, catList);
        CatListView.setAdapter(adapter);

        setupOnclickListeners();
    }

    // The method below populates the category list with data from DB when the application is run (Firebase, 2022)
    private void InitListData(DatabaseReference ref){
        catList.clear();


        //gets snapshot of all categories
        ref.child("categories").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Category_Information obj;
                //adds every category object in DB to catList
                for(DataSnapshot ds : task.getResult().getChildren()){
                    if (Objects.equals(ds.child("uid").getValue(String.class), user.getUid())){
                        obj = ds.getValue(Category_Information.class);
                        //obj = new Category_Information(ds.getKey(), ds.child("category_Colour").getValue(Integer.class), ds.child("category_Name").getValue(String.class), ds.child("category_Description").getValue(String.class))
                        catList.add(obj);
                    }
                }

                //Setups listview and connects adapter (The IIE, 2022)
                SetupListView();
            }
        });
    }

    // The method below sets up the UI by assigning each button on the page (The IIE, 2022)
    private void setupUI() {
        btnAddCategory = (Button) findViewById(R.id.DashboardAddButton);
      //  btnViewAllItems = (Button) findViewById(R.id.DashboardViewAllButton);
        btnViewAllItems = (Button) findViewById(R.id.DashboardDeleteButton);
    }

    // The following is a click method that executes when the add button is clicked (The IIE, 2022)
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

        // The on click method is used to get a specific category (The IIE, 2022)s
        CatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Category_Information catObj = (Category_Information) (CatListView.getItemAtPosition(position));
                Intent displayCatItems = new Intent(Dashboard_Activity.this, ItemPage.class);
                displayCatItems.putExtra("catID", catObj.getCatID());
               // displayCatItems.putExtra("bgColour", catObj.getCategory_Colour());
                displayCatItems.putExtra("catName", catObj.getCategory_Name());
                startActivity(displayCatItems);
            }
        });

    }

    // override the onOptionsItemSelected (The IIE, 2022)
    // function to implement (The IIE, 2022)
    // the item click listener callback (The IIE, 2022)
    // to open and close the navigation (The IIE, 2022)
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
        // Handle navigation view item clicks here (The IIE, 2022).
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            //Go to dashboard (The IIE, 2022)
            Intent dashB = new Intent(this, Dashboard_Activity.class);
            startActivity(dashB);
        } else if (id == R.id.nav_shopping_list){
            //Go to shopping list page (The IIE, 2022)
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

    // The method below redirects the user to the add category page (The IIE, 2022)
    public void AddCategory (View v) {
        Intent addCat = new Intent(this, Category_Page.class);
        startActivity(addCat);
    }

    // The method below redirects the user to the view all items page (The IIE, 2022)
    public void GoToViewAllItem (View v) {
        Intent viewItemAll = new Intent(this, item_list_all.class);
        startActivity(viewItemAll);
    }

}