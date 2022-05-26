package com.example.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.UsernameEditText);
        password = (EditText) findViewById(R.id.PasswordEditText);
        btn_register = (Button) findViewById(R.id.RegisterButton);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();

            }
        });
    }
boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
}
     void checkDataEntered() {
        if(isEmpty(username)){
            Toast t = Toast.makeText(this,"You must enter a username", Toast.LENGTH_SHORT);
            t.show();
        }
        if(isEmpty(password)){
            password.setError("Password is required");
        }
    }
}

    //String Username = username.getText().toString();
   // String Password = password.getText().toString();

           //     if (username.equals("") || password.equals("")) {
              //          Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
              //          } else {
                 //       Toast.makeText(getApplicationContext(), "User name taken", Toast.LENGTH_SHORT).show();
                 //       }
