package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import ST10119385.ChloeMoodley.Add_Item_Page;
import ST10119385.ChloeMoodley.Category_Page;

public class Dashboard_Activity extends AppCompatActivity {
    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // drawer layout instance to toggle the menu icon to open
        //drawer and back button to close drawer (geeksforgeeks.org, 2022).
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button (geeksforgeeks.org, 2022).
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar (geeksforgeeks.org, 2022).
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    public void AddCategory (View v) {
        Intent addCat = new Intent(this, Category_Page.class);
        startActivity(addCat);
    }

    public void GoToAddItem (View v) {
        Intent addItem = new Intent(this, Add_Item_Page.class);
        startActivity(addItem);
    }

    public void GoToItemList (View v) {
        Intent ViewItemList = new Intent(this, Dashboard_Activity.class);
        startActivity(ViewItemList);
    }
    
     public void GoToViewItem (View v) {
        Intent ViewItem = new Intent(this, opscwork.viewitempagefeatures.ViewItem.class);
        startActivity(ViewItem);
     }

}