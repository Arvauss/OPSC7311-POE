package com.example.ViewItemPage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DataModels.Item_Information;
import com.example.test.R;

import java.util.ArrayList;

public class ViewItem extends AppCompatActivity {

    //Adding array list
    public static ArrayList<Item_Information> itemArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item_page);
    }
}