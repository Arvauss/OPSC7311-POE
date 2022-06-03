package opscwork.viewitempagefeatures;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.test.Dashboard_Activity;
import com.example.test.R;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import ST10119385.ChloeMoodley.Add_Item_Page;
import ST10119385.ChloeMoodley.Item_Information;
import ST10119385.ChloeMoodley.ShoppingList_Page;

public class ViewItem extends AppCompatActivity {

    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView burgerNavigationView;

    //Init of item obj to store info from getIntent()
    Item_Information SelectedItem;
    TextView nameItem ,itemDescription, itemDate, ItemPrice, category, itemCount, DesiredItemCount;
    ImageView itemImage;
    ImageButton decrease, dec_desired;
    ImageButton increase, inc_desired;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item_page);

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

        //calls methods to populate activity
        getSelectedItem();
        setupUI();
        setValues();
        setOnclickListeners();

    }

    private void setupUI() {
         nameItem = (TextView) findViewById(R.id.ItemViewName);
         itemDescription = (TextView) findViewById(R.id.Description);
         itemImage = (ImageView) findViewById(R.id.LettuceImage);
         itemDate = (TextView) findViewById(R.id.DateOfAcquisition);
         ItemPrice = (TextView) findViewById(R.id.Price);
         DesiredItemCount = (TextView) findViewById(R.id.Desired_numOfItems);
         itemCount = (TextView) findViewById(R.id.NumOfItems);
         decrease = (ImageButton) findViewById(R.id.decrease_item_qty);
         increase = (ImageButton) findViewById(R.id.increase_item_qty);
         dec_desired = (ImageButton) findViewById(R.id.decrease_desired_item_qty);
         inc_desired = (ImageButton) findViewById(R.id.increase_desired_item_qty);
    }

    private void setOnclickListeners() {
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectedItem.DecreaseQty();
                //reloads activity to display new info | overridePendingTransition removes blinking transition
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectedItem.IncreaseQty();
                //reloads activity to display new info | overridePendingTransition removes blinking transition
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

        inc_desired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectedItem.IncreaseDesiredQty();
                //reloads activity to display new info | overridePendingTransition removes blinking transition
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });
        dec_desired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SelectedItem.getQty() <= SelectedItem.getDesired_Qty()) {
                    SelectedItem.DecreaseDesiredQty();
                    //reloads activity to display new info | overridePendingTransition removes blinking transition
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
            }
        });
    }

    //Method used to get the selected item
    private void getSelectedItem() {
        Intent previousIntent = getIntent();
        int previousID = previousIntent.getIntExtra("id", 0);
        SelectedItem = ItemPage.ItemArrayList.get(previousID);

        category = (TextView) findViewById(R.id.CategoryNameTextView);
        category.setText(SelectedItem.getCategory());
        if (SelectedItem.getItem_bitmap() != null){
            itemImage.setImageBitmap(SelectedItem.getItem_bitmap());
        }
    }

    //method to assign selected itemObj values to ui elements
    private void setValues() {

        //setting the information of the selected item
        nameItem.setText(SelectedItem.getItem_Name());
        itemDescription.setText(SelectedItem.getItem_Description());
        itemImage.setImageResource(SelectedItem.getItem_image());
        itemDate.setText(SelectedItem.getItem_date());
        ItemPrice.setText(Double.toString(SelectedItem.getItem_Price()));
        itemCount.setText(Integer.toString(SelectedItem.getQty()));
        if (SelectedItem.getQty() > SelectedItem.getDesired_Qty())
        SelectedItem.setDesired_Qty(SelectedItem.getQty());
        DesiredItemCount.setText(Integer.toString(SelectedItem.getDesired_Qty()));

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
}