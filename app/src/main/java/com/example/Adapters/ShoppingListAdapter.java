package com.example.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.DataModels.Item_Information;
import com.example.test.R;

import java.util.ArrayList;

public class ShoppingListAdapter extends ArrayAdapter<Item_Information> {

    public ShoppingListAdapter(Context context, int resource, ArrayList<Item_Information> objects) {
        super(context, resource,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //create object
        Item_Information item = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shopping_list_template,parent,false);
        }

        TextView nameItem = (TextView) convertView.findViewById(R.id.itemnameshoppinglisttemplate);
        ImageView imageItem = (ImageView) convertView.findViewById(R.id.imageshoppinglisttemplate);
        TextView nameCat = (TextView) convertView.findViewById(R.id.catnameshoppinglisttemelate);
        TextView qtyItem = (TextView) convertView.findViewById(R.id.qtyshoppinglisttemeplate);
        TextView priceItem = (TextView) convertView.findViewById(R.id.priceshoppinglisttemeplate);

        nameItem.setText(item.getItem_Name());
        imageItem.setImageResource(item.getItem_image());
        priceItem.setText((int) item.getItem_Price());
        nameCat.setText(item.getCategory());
        qtyItem.setText(item.getQty());

        return convertView;
    }
}
