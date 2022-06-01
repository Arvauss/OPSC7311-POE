package ST10119385.ChloeMoodley;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.test.Dashboard_Activity;
import com.example.test.R;
import com.google.android.material.navigation.NavigationView;

//import com.example.test.R;
//import android.widget.Toastimport androidx.appcompat.app.AppCompatActivity;

public class Category_Page extends AppCompatActivity {

    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView burgerNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_ui_page);

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

        /*
            adapter made to push data (in the array string ) from the strings view to the
            activity_main view so all options display on UI (Android Drop Down List Tutorial, 2016).
         */
        Spinner dropDown = (Spinner) findViewById(R.id.DropDown);   //find spinner by the id

        //array adapter created to get information from the string array in the string view
        ArrayAdapter<String> myAdapt = new ArrayAdapter<String>(Category_Page.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Colours));   //finding the array and layout of the dropdown menu

        myAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //making the adapter a dropdown
        dropDown.setAdapter(myAdapt);   //setting the spinner to the adapter
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

    public boolean colourChangeBackground () {
        Spinner colourID = (Spinner) findViewById(R.id.DropDown);   //find spinner by the id
        String[] co = getResources().getStringArray(R.array.Colours);
        LinearLayout col = (LinearLayout) findViewById(R.id.DASHBOARDIDCARD);

        if (colourID.equals(co [0]))
        {
           //col = R.drawable.green_colour_background;

        }

        else if (colourID.equals(co [1]))
        {

        }

        else if (colourID.equals(co [2]))
        {

        }

        else if (colourID.equals(co [3]))
        {

        }

        else if (colourID.equals(co [4]))
        {

        }

        else if (colourID.equals(co [5]))
        {

        }


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

//android on click = view
    public void GoBackDash (View v) {
        Intent goBackDash = new Intent (this, Dashboard_Activity.class);
        startActivity(goBackDash);
    }
}
