package com.example.AddItemPage;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.DashBoardPage.Dashboard_Activity;
import com.example.ShoppingListPage.ShoppingList;
import com.example.test.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class AddItem extends AppCompatActivity {

    //adding second view and class (Add a Second Activity to your App, 2017).
    private static final String TAG = "MainActivity2";

    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;     //(Android Beginner Tutorial #25- DatePicker Dialog [Choosing a Date from a Dialog Pop-Up], 2017)

    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView burgerNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_ui_page);

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

        /* the datapicker code to allow user to select year, month and day without
        typing it (Android Beginner Tutorial #25  - DatePicker Dialog [Choosing a Date from a Dialog Pop-Up], 2017). */

        displayDate = (TextView) findViewById(R.id.DatePicker); //finding view by the text id

        /*
            When user clicks year, month, and day in a calender format
            will pop up mid page when view goes to the activity_main2 view.
         */
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);  //year will display
                int month = cal.get(Calendar.MONTH);    //month will display
                int day = cal.get(Calendar.DAY_OF_MONTH); //day of month will display

                //already installed in design of calender
                DatePickerDialog di = new DatePickerDialog (AddItem.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener,
                        year, month, day);

                //color of calender
                di.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                di.show();
            }
        });

        //month is automatically set a 0, when month displays we need to add one to make it the month the user selects
        //automatically set it to today's date
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " +month + "/" + day + "/" + year);

                String date = month + "/" +day + "/" +year;
                displayDate.setText(date);
            }
        };
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
            Intent shopList = new Intent(this, ShoppingList.class);
            startActivity(shopList);
        } else if (id == R.id.nav_graph_screen) {
            //Go to Graph page
            //   Intent graphPage = new Intent(this, ShoppingList_Page.class);
            //   startActivity(graphPage);
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

    public void goBackToList (View v) {
        Intent listBackItem = new Intent(this, Dashboard_Activity.class);
        startActivity(listBackItem);
    }
}
