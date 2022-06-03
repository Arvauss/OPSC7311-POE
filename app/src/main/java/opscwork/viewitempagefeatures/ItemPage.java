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
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Dashboard_Activity;
import com.example.test.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ST10119385.ChloeMoodley.Add_Item_Page;
import ST10119385.ChloeMoodley.Category_Information;
import ST10119385.ChloeMoodley.Item_Information;
public class ItemPage extends  AppCompatActivity {

    // Declaration of variables ()
    private static final String TAG = "ItemPage";
    EditText ItemName;
    EditText ItemDescription;
    TextView ItemPurchaseDate;
    EditText ItemPrice;
    ImageView ItemImage;
    ListView mListView;
    Category_Information CurrentCategory;
    TextView Header;

    ActivityResultLauncher<Intent> resultLauncher ;

    // Creation of array list
    public static ArrayList<Item_Information> ItemArrayList = new ArrayList<>();
    Button ConfirmItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_page);
        Log.d(TAG, "onCreate: Started.");



        setupUI();
        //try statement to catch any potential errors (W3Schools, 2022)
        //https://www.w3schools.com/java/java_try_catch.asp
        try {GetCategory();} catch (Exception e) { Log.d("ADDITEM", e.toString());};
        if (ItemArrayList.isEmpty())
            InitListData();
        setupListView();
        setupOnClickListeners();


    }

    private void GetCategory(){
        Intent prevIntent = getIntent();
        int pos = prevIntent.getIntExtra("id", 0);
        CurrentCategory =  Dashboard_Activity.catList.get(pos);
        Header.setText(CurrentCategory.getCategory_Name());

    }
    private void GetCategory(int pos){
        CurrentCategory =  Dashboard_Activity.catList.get(pos);
        Header.setText(CurrentCategory.getCategory_Name());

    }

    public void GoToAddItem (View v) {
        Intent addItem = new Intent(this, Add_Item_Page.class);
        addItem.putExtra("categoryName", CurrentCategory.getCategory_Name());
        resultLauncher.launch(addItem);

    }

    private void setupOnClickListeners() {
        //Gets selected item in listview and starts intent to view item info on listview item click
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Item_Information itemObj = (Item_Information) (mListView.getItemAtPosition(position));
                Intent displayItem = new Intent(getApplicationContext(), ViewItem.class);
                displayItem.putExtra("id", position);
                startActivity(displayItem);
            }
        });

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == 1){
                            Intent prevIntent = result.getData();
                            int position = prevIntent.getIntExtra("id", 0);
                            GetCategory(position);
                        }
                    }
                });
    }
    private void setupListView() {
        mListView = (ListView) findViewById(R.id.itemListView);
        ArrayList<Item_Information> CatItems = new ArrayList<Item_Information>();
        for (Item_Information item: ItemArrayList) {
            if (item.getCategory().equals(CurrentCategory.getCategory_Name())){
                CatItems.add(item);
            }
        }
        // Creation of adapter
        ItemList adapter = new ItemList(this, R.layout.item_list_template,CatItems);
        mListView.setAdapter(adapter);

    }

    private void setupUI() {
        ItemName = (EditText) findViewById(R.id.ItemNameBox);
        ItemDescription = (EditText) findViewById(R.id.ItemDescTextBox);
        ItemPurchaseDate = (TextView) findViewById(R.id.DatePicker);
        ItemPrice = (EditText) findViewById(R.id.priceTextBox);
        ItemImage = (ImageView) findViewById(R.id.ImageItemPic);
        Header = (TextView) findViewById(R.id.ItemListHeader);

    }
    private void InitListData() {
        // Creation of item objects and adding them to ArrayList
        Item_Information obj1 = new Item_Information
                ("Item1",
                        "Item1 Desc",
                        R.drawable.bodega_image,
                        "28/05/2022",
                        11.11,
                        CurrentCategory.getCategory_Name(),
                        4);
        ItemArrayList.add(obj1);
        Item_Information obj2 = new Item_Information
                ("Item2",
                        "Item2 Desc",
                        R.drawable.bodega_image,
                        "29/05/2022",
                        22.22,
                        CurrentCategory.getCategory_Name(),
                        5);
        ItemArrayList.add(obj2);
        Item_Information obj3 = new Item_Information
                ("Item3",
                        "Item3 Desc",
                        R.drawable.bodega_image,
                        "28/05/2022",
                        33.33,
                        CurrentCategory.getCategory_Name(),
                        6);
        ItemArrayList.add(obj3);
    }





}