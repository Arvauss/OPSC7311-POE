package com.example.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password;
    Button btn_register;
    TextView login_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.UsernameEditText);
        password = (EditText) findViewById(R.id.PasswordEditText);
        btn_register = (Button) findViewById(R.id.RegisterButton);
        login_link = (TextView) findViewById(R.id.LoginHyperLink);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();

            }
        });

        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), login.class);
                view.getContext().startActivity(intent);
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


    public void GoToLogin(View view){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}

    //String Username = username.getText().toString();
   // String Password = password.getText().toString();

           //     if (username.equals("") || password.equals("")) {
              //          Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
              //          } else {
                 //       Toast.makeText(getApplicationContext(), "User name taken", Toast.LENGTH_SHORT).show();
                 //       }
