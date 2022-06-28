package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import ST10119385.ChloeMoodley.Category_Information;

public class DashAdapter extends ArrayAdapter<Category_Information> {

    // This constructor is used for the dash board adapter (The IIE, 2022)
    public DashAdapter(@NonNull Context context, int resource, List<Category_Information> catList) {
        super(context, resource, catList);
    }

    // This method retrieves the template that will store the card list (The IIE, 2022)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Category_Information catObj = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_card_layout, parent, false);
        }

        // The code below assigns each element from the dash board to the variables declared (The IIE, 2022)
        ImageView img = (ImageView) convertView.findViewById(R.id.img_Category_Image);
        TextView catName = (TextView) convertView.findViewById(R.id.txt_Category_Name);
        TextView catDesc = (TextView) convertView.findViewById(R.id.txt_Category_Desc);

        // This if-else checks if each element is empty or not (The IIE, 2022)
        if(catObj.getCat_Image()!=null){
            //Category images are set using Picasso library (Picasso, 2022)
            Picasso.get().load(catObj.getCat_Image()).resize(150,150).centerCrop().into(img);}

        // The code below sets the category name and description (The IIE, 2022)
        catName.setText(catObj.getCategory_Name());
        catDesc.setText(catObj.getCategory_Description());

        convertView.setBackgroundColor(catObj.getCategory_Colour());

        // This returns the convert view (The IIE, 2022)
        return convertView;
    }
}
