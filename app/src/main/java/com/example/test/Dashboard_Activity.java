package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ST10119385.ChloeMoodley.Add_Item_Page;
import ST10119385.ChloeMoodley.Category_Page;
import opscwork.viewitempagefeatures.R;
public class Dashboard_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void AddCategory (View v) {
        Intent addCat = new Intent(this, Category_Page.class);
        startActivity(addCat);
    }

    public void GoToAddItem (View v) {
        Intent addItem = new Intent(this, Add_Item_Page.class);
        startActivity(addItem);
    }

    public void GoToItemList (View v) {
        Intent ViewItemList = new Intent(this, Dashboard_Activity.class);
        startActivity(ViewItemList);
    }
    
     public void GoToViewItem (View v) {
        Intent ViewItem = new Intent(this, opscwork.viewitempagefeatures.ViewItem.class);
        startActivity(ViewItem);
     }

}