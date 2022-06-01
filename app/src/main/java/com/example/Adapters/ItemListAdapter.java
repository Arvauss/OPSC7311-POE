package com.example.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.DataModels.Item_Information;
import com.example.test.R;

import java.util.ArrayList;

public class ItemListAdapter extends ArrayAdapter<Item_Information> {

    // Declaration of variables
    private static final String TAG = "ItemList";
    private Context mContext;
    int mitem_list_template;

    public ItemListAdapter(Context context, int item_list_template, ArrayList<Item_Information> itemArrayList) {
        super(context, item_list_template,itemArrayList);
        this.mContext = context;
        mitem_list_template = item_list_template;
    }

    // Overide method ()
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String ItemName = getItem(position).getItem_Name();
        int ItemImage = getItem(position).getItem_image();

        // Creation of item information object ()
        Item_Information obj = new Item_Information(ItemImage, ItemName);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mitem_list_template,parent,false);

        TextView EdItemName = (TextView) convertView.findViewById(R.id.ItemNameForItemPage);
        ImageView EdItemImage = (ImageView) convertView.findViewById(R.id.ImageIDforItem);

        EdItemName.setText(ItemName);
        EdItemImage.setImageResource(ItemImage);

        return convertView;
    }
}
