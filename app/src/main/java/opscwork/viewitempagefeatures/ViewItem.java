package opscwork.viewitempagefeatures;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.test.Dashboard_Activity;
import com.example.test.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import ST10119385.ChloeMoodley.Category_Information;
import ST10119385.ChloeMoodley.Item_Information;
import ST10119385.ChloeMoodley.ShoppingList_Page;

public class ViewItem extends AppCompatActivity {

    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView burgerNavigationView;

    DatabaseReference dbRef;
    private FirebaseAuth Auth;
    FirebaseUser user;

    //Init of item obj to store info from getIntent (The IIE, 2022)
    Item_Information SelectedItem;
    TextView nameItem ,itemDescription, itemDate, ItemPrice, category, itemCount, DesiredItemCount;
    ImageView itemImage;
    ImageButton decrease, dec_desired;
    ImageButton increase, inc_desired;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item_page);

        dbRef = FirebaseDatabase.getInstance("https://bodegaapp-opscpoe-default-rtdb.firebaseio.com/").getReference();


        // drawer layout instance to toggle the menu icon to open (The IIE, 2022)
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

        //calls methods to populate activity (The IIE, 2022)
        getSelectedItem();



    }

    // The code below sets up the UI in order to get the data entered by the user (The IIE, 2022)
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

        setValues();
        setOnclickListeners();
    }

    // The code below is an on click method that executes when the decreaseqty button is clicked (The IIE, 2022)
    private void setOnclickListeners() {
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectedItem.DecreaseQty();
                //reloads activity to display new info | overridePendingTransition removes blinking transition (The IIE, 2022)
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
                //reloads activity to display new info | overridePendingTransition removes blinking transition (The IIE, 2022)
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
                //reloads activity to display new info | overridePendingTransition removes blinking transition (The IIE, 2022)
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
                    //reloads activity to display new info | overridePendingTransition removes blinking transition (The IIE, 2022)
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
            }
        });
    }

    //Method used to get the selected item (The IIE, 2022)
    private void getSelectedItem() {
        Intent previousIntent = getIntent();
        String itemID = previousIntent.getStringExtra("id");

        dbRef.child("items").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                for(DataSnapshot ds : task.getResult().getChildren()){
                    if(Objects.equals(ds.child("item_ID").getValue(String.class), itemID)) {
                        SelectedItem = ds.getValue(Item_Information.class);
                        break;
                    }
                }

                category = (TextView) findViewById(R.id.CategoryNameTextView);
                category.setText(SelectedItem.getCategory());

                setupUI();
            }
        });

    }

    //method to assign selected itemObj values to ui elements (The IIE, 2022)
    private void setValues() {

        //setting the information of the selected item (The IIE, 2022)
        nameItem.setText(SelectedItem.getItem_Name());
        itemDescription.setText(SelectedItem.getItem_Description());
        itemDate.setText(SelectedItem.getItem_date());
        ItemPrice.setText(Double.toString(SelectedItem.getItem_Price()));
        itemCount.setText(Integer.toString(SelectedItem.getQty()));
        DesiredItemCount.setText(Integer.toString(SelectedItem.getDesired_Qty()));
        if (SelectedItem.getItem_img() != null){
            Picasso.get().load(SelectedItem.getItem_img()).resize(150,150).centerCrop().into(itemImage);}

        ProgressBar itemProgressBar = (ProgressBar) findViewById(R.id.itemProgressGraph);



        //Get quantity of the specific item
        int quantity = SelectedItem.getQty();
        //Get desired quantity of the specific item
        int desiredQuantity = SelectedItem.getDesired_Qty();
        int progress = (quantity/desiredQuantity)*100;

        itemProgressBar.setMax(desiredQuantity);
        itemProgressBar.setProgress(quantity);

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
        // Handle navigation view item clicks here (The IIE, 2022)
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
            //Go to Graph page (The IIE, 2022)
            //   Intent graphPage = new Intent(this, ShoppingList_Page.class);
            //   startActivity(graphPage);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}