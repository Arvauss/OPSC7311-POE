package opscwork.viewitempagefeatures;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

import ST10119385.ChloeMoodley.Add_Item_Page;
import ST10119385.ChloeMoodley.Category_Information;
import ST10119385.ChloeMoodley.Graph_Page;
import ST10119385.ChloeMoodley.Item_Information;
import ST10119385.ChloeMoodley.ShoppingList_Page;

public class ItemPage extends  AppCompatActivity {
    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView burgerNavigationView;

    // Declaration of variables (The IIE, 2022)
    private static final String TAG = "ItemPage";
    EditText ItemName;
    EditText ItemDescription;
    TextView ItemPurchaseDate;
    EditText ItemPrice;
    ImageView ItemImage;
    ListView mListView;
    Category_Information CurrentCategory;
    TextView Header;
    Button AddItem;

    ActivityResultLauncher<Intent> resultLauncher ;

    String curCatName, curCatID;
    int curCatColour;

    DatabaseReference dbRef;

    // Creation of array list (The IIE, 2022)
    public static ArrayList<Item_Information> ItemArrayList = new ArrayList<>();
    Button ConfirmItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_page);
        Log.d(TAG, "onCreate: Started.");

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

        setupUI();
        //try statement to catch any potential errors (W3Schools, 2022)
        //https://www.w3schools.com/java/java_try_catch.asp
        try {GetCategory();} catch (Exception e) { Log.d("ADDITEM", e.toString());};

    }

    // The code below gets the category in order to for each item to have a category (The IIE, 2022)
    private void GetCategory(){
        Intent prevIntent = getIntent();
        curCatID = prevIntent.getStringExtra("catID");
        curCatName = prevIntent.getStringExtra("catName");

        DatabaseReference catRef = dbRef.child("categories");

        catRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                //adds every category object in DB to catList
                for(DataSnapshot ds : task.getResult().getChildren()){
                    if (Objects.equals(ds.getKey(), curCatID)){
                        CurrentCategory = ds.getValue(Category_Information.class);
                        break;
                    }
                }
                Header.setText(CurrentCategory.getCategory_Name());
                //Initialises list data
                InitListData();
            }
        });
    }

    private void setupOnClickListeners() {
        //Gets selected item in listview and starts intent to view item info on listview item click (The IIE, 2022)
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Item_Information itemObj = (Item_Information) (mListView.getItemAtPosition(position));
                Intent displayItem = new Intent(getApplicationContext(), ViewItem.class);
                displayItem.putExtra("id", itemObj.getItem_ID());
                startActivity(displayItem);
            }
        });

        AddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addItem = new Intent(getApplicationContext(), Add_Item_Page.class);
                addItem.putExtra("catID", curCatID);
                addItem.putExtra("catName", curCatName);
                startActivity(addItem);
            }
        });



        /*resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == 1){
                            Intent prevIntent = result.getData();
                            int position = prevIntent.getIntExtra("id", 0);
                            GetCategory(position);
                        }
                    }
                });*/
    }

    // The code below populates the list view (The IIE, 2022)
    private void setupListView() {
        mListView = (ListView) findViewById(R.id.itemListView);

        // Creation of adapter (The IIE, 2022)
        ItemList adapter = new ItemList(this, R.layout.item_list_template,ItemArrayList);
        mListView.setAdapter(adapter);
      //  mListView.setBackgroundColor(curCatColour);

        setupOnClickListeners();

    }

    // The code below sets up the UI in order to retrieve the data in each layout element (The IIE, 2022)
    private void setupUI() {
        ItemName = (EditText) findViewById(R.id.ItemNameBox);
        ItemDescription = (EditText) findViewById(R.id.ItemDescTextBox);
        ItemPurchaseDate = (TextView) findViewById(R.id.DatePicker);
        ItemPrice = (EditText) findViewById(R.id.priceTextBox);
        ItemImage = (ImageView) findViewById(R.id.ImageItemPic);
        Header = (TextView) findViewById(R.id.ItemListHeader);
        AddItem = (Button) findViewById(R.id.ItemListAdd);
    }

    // The code below populates the list view with data (The IIE, 2022)
    private void InitListData() {
        ItemArrayList.clear();

        DatabaseReference itmRef = dbRef.child("items");

        //gets snapshot of all items
        itmRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Item_Information item;
                //adds every category object in DB to catList
                for(DataSnapshot ds : task.getResult().getChildren()){
                    item = ds.getValue(Item_Information.class);
                    if (item.getCat_ID() !=null )
                        if (item.getCat_ID().equals(curCatID))
                            ItemArrayList.add(item);
                }

                //Setups listview and connects adapter (The IIE, 2022)
                setupListView();

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
            Intent graphPage = new Intent(this, Graph_Page.class);
            startActivity(graphPage);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}