package ST10119385.ChloeMoodley;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

import opscwork.viewitempagefeatures.ItemList;
import opscwork.viewitempagefeatures.ViewItem;

public class ShoppingList_Page extends AppCompatActivity {

    private static final String TAG ="ShoppingList_Page";
    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView burgerNavigationView;
    public double Total;

    TextView ItemNameShoppingList;
    TextView CategoryNameShoppingList;
    ImageView ItemImageShoppingList;
    TextView ItemPriceShoppingList;
    TextView ItemQuantityShoppingList;
    TextView ItemTotalCost;
    ListView mList;

    DatabaseReference dbRef;
    FirebaseAuth Auth;
    FirebaseUser user;

    ArrayList<Item_Information> ItemList = new ArrayList<>();
    ArrayList<Category_Information> catList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list_page);

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

        Log.d(TAG, "onCreate : Started.");

        InitListData();

    }

    public void setupListView(){
        mList = (ListView) findViewById(R.id.shoppingListView);
        ItemTotalCost = (TextView) findViewById(R.id.CalculatedCostValue);

        Item_Information item;

        ArrayList<Item_Information> ShoppingListArrayList = new ArrayList<Item_Information>();

       // ShoppingListArrayList.add((slo));
        ShoppingList_Adapter adp = new ShoppingList_Adapter(this, R.layout.shopping_list_template, ShoppingListArrayList);
        mList.setAdapter(adp);

        for (Item_Information ListItem: ItemList) {
            if (ListItem.getDesired_Qty() > ListItem.getQty())
                ShoppingListArrayList.add(ListItem);
        }

        // The for loop goes through the array list in order to get the total cost(The IIE, 2022)
        for(Item_Information ShoppinglistItem: ShoppingListArrayList){
            ItemTotalCost.setText("");

            int RetrievedQty = ShoppinglistItem.getQty();
            int RetrievedDesiredQty = ShoppinglistItem.getDesired_Qty();
            double RetrievedPrice = ShoppinglistItem.getItem_Price();

            Total +=  (RetrievedDesiredQty - RetrievedQty) * RetrievedPrice;
            ItemTotalCost.setText("R " + Total);
        }

        setOnClickListeners();

    }

    //On click listener used to show list of shopping items
    private void setOnClickListeners() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Item_Information itemObj = (Item_Information) (mList.getItemAtPosition(position));
                Intent displayList = new Intent(getApplicationContext(), ViewItem.class);
                displayList.putExtra("id", position);
                startActivity(displayList);
            }
        });
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
                        //obj = new Category_Information(ds.getKey(), ds.child("category_Colour").getValue(Integer.class), ds.child("category_Name").getValue(String.class), ds.child("category_Description").getValue(String.class))
                        catList.add(catObj);
                    }
                }
                PopulateItemList();
            }
        });

    }

    private void PopulateItemList(){
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

                //Setups listview and connects adapter (The IIE, 2022)
                setupListView();

            }
        });
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
