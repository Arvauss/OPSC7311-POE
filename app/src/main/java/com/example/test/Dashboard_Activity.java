package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.register.RegisterActivity;
import com.example.test.R;

public class Dashboard_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void addItemListButton (View view){
        Intent addItemButton = new Intent(this, ST10119385.ChloeMoodley.Add_Item_Page.class);
        startActivity(addItemButton);
    }
}