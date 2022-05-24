package com.example.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btn_register = (Button) findViewById(R.id.registerbtn);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEnter();

                String Username = username.getText().toString();
                String Password = password.getText().toString();

                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "User name taken", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

     void checkDataEnter() {
    }
}