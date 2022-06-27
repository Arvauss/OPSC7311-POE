package opscwork.viewitempagefeatures;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Dashboard_Activity;
import com.example.test.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

import ST10119385.ChloeMoodley.Add_Item_Page;
import ST10119385.ChloeMoodley.Category_Information;
import ST10119385.ChloeMoodley.Item_Information;

public class ItemPage extends  AppCompatActivity {

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
                displayItem.putExtra("id", position);
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
}