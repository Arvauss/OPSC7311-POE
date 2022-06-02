package ST10119385.ChloeMoodley;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_all);
        Log.d(TAG, "onCreate: Started.");

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

    private void setupOnClickListeners() {
        //Gets selected item in listview and starts intent to view item info on listview item click
        ListViewAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Item_Information itemObj = (Item_Information) (ListViewAll.getItemAtPosition(position));
                Intent displayItem = new Intent(getApplicationContext(), ViewItem.class);
                displayItem.putExtra("name", itemObj.getItem_Name());
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
        Item_Information obj1 = new Item_Information
                ("Item1", "Item1 Desc", R.drawable.bodega_image, "28/05/2022", 11.11, 4);
        ItemPage.ItemArrayList.add(obj1);
    }



}