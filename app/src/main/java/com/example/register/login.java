package com.example.register;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public abstract class login extends AppCompatActivity implements View.OnClickListener {

    public void myMethod(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void LoginPageDataModel(){

    }

    /*TextView RegisterHyperLink = (TextView) this.findViewById(R.id.RegisterHyperLink);
    RegisterHyperLink.setOnClickListener(new OnClickListener(){

    }

   textView.setOnClickListener(new View.OnClickListener){
       public void onClick(View RegisterHyperLink){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

     */
        }


