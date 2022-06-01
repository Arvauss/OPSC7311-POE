package com.example.ItemListPage;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Adapters.ItemListAdapter;
import com.example.DataModels.Item_Information;
import com.example.test.R;

import java.util.ArrayList;

public class Item extends AppCompatActivity {

    // Declaration of variables ()
    private static final String TAG = "ItemPage";
    EditText ItemName;
    ImageView ItemImage;
    Button ConfirmItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_page);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.itemListView);
        ItemName = (EditText) findViewById(R.id.ItemNameBox);
        ItemImage = (ImageView) findViewById(R.id.ImageItemPic);

        // Creation of item object ()
        Item_Information obj = new Item_Information(int.class.cast(ItemImage), ItemName.toString());

        // Creation of array list and adding item_information object to the list ()
        ArrayList<Item_Information> ItemArrayList = new ArrayList<>();
        ItemArrayList.add(obj);

        // Creation of adapter
        ItemListAdapter adapter = new ItemListAdapter(this, R.layout.item_list_template, ItemArrayList);
        mListView.setAdapter(adapter);

    }
}