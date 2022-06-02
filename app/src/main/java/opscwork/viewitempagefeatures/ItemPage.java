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

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Dashboard_Activity;
import com.example.test.R;

import java.util.ArrayList;

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

    // Creation of array list
    public static ArrayList<Item_Information> ItemArrayList = new ArrayList<>();
    Button ConfirmItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_page);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.itemListView);
        ItemName = (EditText) findViewById(R.id.ItemNameBox);
        ItemDescription = (EditText) findViewById(R.id.ItemDescTextBox);
        ItemPurchaseDate = (TextView) findViewById(R.id.DatePicker);
        ItemPrice = (EditText) findViewById(R.id.priceTextBox);
        ItemImage = (ImageView) findViewById(R.id.ImageItemPic);

        setupUI();
        setupListView();
        getCategory();
        InitListData();
        setupOnClickListeners();
        // Creation of item object ()
        Item_Information obj = new Item_Information(ItemName.toString(),ItemDescription.toString(),ItemPurchaseDate.toString(),double.class.cast(ItemPrice));

        // Creation of array list and adding item_information object to the list ()
        ArrayList<Item_Information> ItemArrayList = new ArrayList<>();
        ItemArrayList.add(obj);

        // Creation of adapter
        ItemList adapter = new ItemList(this, R.layout.item_list_template,ItemArrayList);
        mListView.setAdapter(adapter);


    }
    private void getCategory() {
        Intent previousIntent = getIntent();
        int CatName = previousIntent.getIntExtra("id", 0);
        //Get category based on name received from intent utilising indexOf (TutorialsPoint, 2019);
        //https://www.tutorialspoint.com/get-the-index-of-a-particular-element-in-an-arraylist-in-java#
        CurrentCategory = Dashboard_Activity.catList.get(CatName);
    }

    private void setupOnClickListeners() {
        //Gets selected item in listview and starts intent to view item info on listview item click
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Item_Information itemObj = (Item_Information) (mListView.getItemAtPosition(position));
                Intent displayItem = new Intent(getApplicationContext(), ViewItem.class);
                displayItem.putExtra("name", itemObj.getItem_Name());
                startActivity(displayItem);
            }
        });
    }
    private void setupListView() {
        mListView = (ListView) findViewById(R.id.itemListView);
        // Creation of adapter
        ItemList adapter = new ItemList(this, R.layout.item_list_template,ItemArrayList);
        mListView.setAdapter(adapter);

    }

    private void setupUI() {
        ItemName = (EditText) findViewById(R.id.ItemNameBox);
        ItemDescription = (EditText) findViewById(R.id.ItemDescTextBox);
        ItemPurchaseDate = (TextView) findViewById(R.id.DatePicker);
        ItemPrice = (EditText) findViewById(R.id.priceTextBox);

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