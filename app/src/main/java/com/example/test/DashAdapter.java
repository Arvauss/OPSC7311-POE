package com.example.test;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

import ST10119385.ChloeMoodley.Category_Information;

public class DashAdapter extends ArrayAdapter<Category_Information> {


    public DashAdapter(@NonNull Context context, int resource, List<Category_Information> catList) {
        super(context, resource, catList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Category_Information catObj = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_card_layout, parent, false);
        }

        ImageView img = (ImageView) convertView.findViewById(R.id.img_Category_Image);
        TextView catName = (TextView) convertView.findViewById(R.id.txt_Category_Name);
        TextView catDesc = (TextView) convertView.findViewById(R.id.txt_Category_Desc);

        img.setImageResource(catObj.getCategory_Icon());
        catName.setText(catObj.getCategory_Name());
        catDesc.setText(catObj.getCategory_Description());

        convertView.setBackgroundColor(catObj.getCategory_Colour());

        return convertView;
    }
}
