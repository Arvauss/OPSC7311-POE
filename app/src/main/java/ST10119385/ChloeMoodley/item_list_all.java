package ST10119385.ChloeMoodley;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.test.Dashboard_Activity;
import com.example.test.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import opscwork.viewitempagefeatures.ItemList;
import opscwork.viewitempagefeatures.ItemPage;
import opscwork.viewitempagefeatures.ViewItem;

public class item_list_all extends AppCompatActivity {

    // Declaration of variables ()
    private static final String TAG = "item_list_all";
    EditText ItemNameAll;
    EditText ItemDescriptionAll;
    TextView ItemPurchaseDateAll;
    EditText ItemPriceAll;
    ImageView ItemImageAll;
    ListView ListViewAll;

    DatabaseReference dbRef;

    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView burgerNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_all);
        Log.d(TAG, "onCreate: Started.");

        dbRef = FirebaseDatabase.getInstance("https://bodegaapp-opscpoe-default-rtdb.firebaseio.com/").getReference();

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

        ListViewAll = (ListView) findViewById(R.id.itemListAllView);
        // Creation of adapter
        ItemList adp = new ItemList(this, R.layout.item_list_template, ItemPage.ItemArrayList);
        ListViewAll.setAdapter(adp);

        ItemNameAll = (EditText) findViewById(R.id.ItemNameBox);
        ItemDescriptionAll = (EditText) findViewById(R.id.ItemDescTextBox);
        ItemPurchaseDateAll = (TextView) findViewById(R.id.DatePicker);
        ItemPriceAll = (EditText) findViewById(R.id.priceTextBox);
        ItemImageAll = (ImageView) findViewById(R.id.ImageItemPic);

        setupUI();
        setupOnClickListeners();
        DisplayListData();

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

    private void setupOnClickListeners() {
        //Gets selected item in listview and starts intent to view item info on listview item click
        ListViewAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Item_Information itemObj = (Item_Information) (ListViewAll.getItemAtPosition(position));
                Intent displayItem = new Intent(getApplicationContext(), ViewItem.class);
                displayItem.putExtra("id", position);
                startActivity(displayItem);
            }
        });
    }

    private void setupUI() {
        ItemNameAll = (EditText) findViewById(R.id.ItemNameBox);
        ItemDescriptionAll = (EditText) findViewById(R.id.ItemDescTextBox);
        ItemPurchaseDateAll = (TextView) findViewById(R.id.DatePicker);
        ItemPriceAll = (EditText) findViewById(R.id.priceTextBox);
    }

    private void DisplayListData() {
        // Creation of item objects and adding them to ArrayList

    }



}