package opscwork.viewitempagefeatures;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ST10119385.ChloeMoodley.Item_Information;

public class ItemPage extends  AppCompatActivity {

    // Declaration of variables ()
    private static final String TAG = "ItemPage";
    EditText ItemName;
    EditText ItemDescription;
    TextView ItemPurchaseDate;
    EditText ItemPrice;
    ImageView ItemImage;

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

        // Creation of item object ()
        Item_Information obj = new Item_Information(ItemName.toString(),ItemDescription.toString(),ItemPurchaseDate.toString(),double.class.cast(ItemPrice));

        // Creation of array list and adding item_information object to the list ()
        ArrayList<Item_Information> ItemArrayList = new ArrayList<>();
        ItemArrayList.add(obj);

        // Creation of adapter
        ItemList adapter = new ItemList(this, R.layout.item_list_template,ItemArrayList);
        mListView.setAdapter(adapter);


    }
}