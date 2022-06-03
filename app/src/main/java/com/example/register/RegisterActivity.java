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

    // Declaration of variables (The IIE, 2022)
    EditText username, password;
    Button btn_register;
    TextView login_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Storing data from different elements into the variables (The IIE, 2022)
        username = (EditText) findViewById(R.id.RegUsernameEditText);
        password = (EditText) findViewById(R.id.RegPasswordEditText);
        btn_register = (Button) findViewById(R.id.RegisterButton);
        login_link = (TextView) findViewById(R.id.LoginHyperLink);

        // This is a click method when the register button is clicked (The IIE, 2022)
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDataEntered()){
                    LoginPageDataModel NewUser = new LoginPageDataModel(username.getText().toString(), password.getText().toString());
                    login.userList.add(NewUser);
                    Toast t = Toast.makeText(getApplicationContext(), "New user created", Toast.LENGTH_SHORT);
                    t.show();
                    Intent intent = new Intent(getApplicationContext(), login.class);
                    intent.putExtra("username", username.toString());
                    intent.putExtra("password", password.toString());
                    startActivity(intent);
                }

            }
        });

        // This is a click method whihc executes when the login link is clicked (The IIE, 2022)
        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), login.class);
                view.getContext().startActivity(intent);
            }
        });


    }

    // This method checks if the textboxes are empty (The IIE, 2022)
    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    // This method forces the user to enter a username and password (The IIE, 2022)
    public boolean checkDataEntered() {

        if(isEmpty(username)){
            Toast t = Toast.makeText(this,"You must enter a username", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        if(isEmpty(password)){
            password.setError("Password is required");
            return false;
        }
        return true;
    }


    // This method redirects the user to the login page after they have registered (The IIE, 2022)
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
