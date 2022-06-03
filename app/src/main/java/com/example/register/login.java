package com.example.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Dashboard_Activity;
import com.example.test.R;

import java.util.ArrayList;

public class login extends AppCompatActivity  {

    // Declaration of variables (The IIE, 2022)
    EditText Username, Password;
    Button LoginButton;

    // Creation of Array List (The IIE, 2022)
    public static ArrayList<LoginPageDataModel> userList = new ArrayList<LoginPageDataModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        if (userList.size() == 0){
            LoginPageDataModel TestUser = new LoginPageDataModel("Testing.com", "Password123");
            userList.add(TestUser);
        }

        // Calling of methods (The IIE, 2022)
        setupUI();
        setupListener();
    }

    // This method retreives the data entered in each of the textboxes as well as the login button (The IIE, 2022)
    private void setupUI(){
        Username = findViewById(R.id.UsernameEditText);
        Password =  findViewById(R.id.PasswordEditText);
        LoginButton = findViewById(R.id.LoginButton);
    }

    // This method verifies the entered username (The IIE, 2022)
    private void setupListener() {
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (verifyUsername()){
                   Login(view);
               } else {
                   //prompt if user makes a wong input on password or username (The IIE, 2022)
                   Toast t = Toast.makeText(getApplicationContext(), "Wrong username or Password", Toast.LENGTH_SHORT);
                   t.show();
               }
            }
        });

    }

    //This method is used to verify the username and password (The IIE, 2022)
     public boolean verifyUsername() {

        //Method uses a boolean to check if the enter data is eiter true or false (The IIE, 2022)
         boolean isInputValid = true;

         //If statement used to show an error message if the username text is empty (The IIE, 2022)
         if (isEmpty(Username)) {
             Username.setError("Enter username to login");
             isInputValid = false;
         }
         //If statement used to show an error message if the password text is empty (The IIE, 2022)
         if (isEmpty(Password)) {
             Password.setError("Enter password to login");
             isInputValid = false;
         } else {
             //Else if statement is used to check if the user password is longer than 4 characers (The IIE, 2022)
             if (Password.getText().toString().length() < 4) {
                 Password.setError("Password must be longer than 4 characters");
                 isInputValid = false;
             }
         }

         boolean isUserValid = false;

         //This if statement is used to validate the username and password (The IIE, 2022)
         if (isInputValid) {
             String UsernameVal = Username.getText().toString();
             String PasswordVal = Password.getText().toString();
             //If statement evaluates if the password matches the username (The IIE, 2022)
             for (LoginPageDataModel user: userList) {
                 if(UsernameVal.equals(user.getUsername()) && PasswordVal.equals(user.getPassword())){
                     isUserValid = true;
                     return isUserValid;
                 }
             }
         }
         return isUserValid;
     }

     //boolean is used to evaluate any empty editTexts (The IIE, 2022)
    boolean isEmpty(EditText Username) {
        CharSequence str = Username.getText().toString();
        return TextUtils.isEmpty(str);
    }

    // This redirets the user to the register page (The IIE, 2022)
    public void GoToRegister(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    //Opening the Dashboard page when logged in (The IIE, 2022)
    public void Login(View view){
        Intent intent = new Intent(this, Dashboard_Activity.class);
        startActivity(intent);
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


