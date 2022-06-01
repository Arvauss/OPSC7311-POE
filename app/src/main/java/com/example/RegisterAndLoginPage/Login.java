package com.example.RegisterAndLoginPage;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DashBoardPage.Dashboard_Activity;
import com.example.test.R;

public class Login extends AppCompatActivity  {

    EditText Username, Password;
    Button LoginButton, Register;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setupUI();
       // setupListener();

    }

    private void setupUI(){
        Username = findViewById(R.id.UsernameEditText);
        Password =  findViewById(R.id.PasswordEditText);
        LoginButton = findViewById(R.id.LoginButton);
    }

    private void setupListener() {
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyUsername();
            }
        });

    }

    //This method is used to verify the username and password
     void verifyUsername() {

        //Method uses a boolean to check if the enter data is eiter true or false
         boolean isValid = true;

         //If statement used to show an error message if the username text is empty
         if (isEmpty(Username)) {
             Username.setError("Enter username to login");
             isValid = false;
         }
         //If statement used to show an error message if the password text is empty
         if (isEmpty(Password)) {
             Password.setError("Enter password to login");
             isValid = false;
         } else {
             //Else if statement is used to check if the user password is longer than 4 characers
             if (Password.getText().toString().length() < 4) {
                 Password.setError("Password must be longer than 4 characters");
                 isValid = false;
             }
         }

         //This if statement is used to validate the username and password
         if (isValid) {
             String UsernameVal = Username.getText().toString();
             String PasswordVal = Password.getText().toString();

             //If statement evaluates if the password matches the username
             if(UsernameVal.equals("Testing.com")&& PasswordVal.equals("Password123")){

                 //Below intent is used to open new activity after login has been checked
                 Intent i = new Intent(Login.this, com.example.RegisterAndLoginPage.Register.class);
                 startActivity(i);
                 //Finish the activity
                 this.finish();

                 //Else statement prompt if user makes a wong input on password or username
             }else{
                 Toast t = Toast.makeText(this,"Wrong username or Password", Toast.LENGTH_SHORT);
                 t.show();
             }
         }
     }

     //boolean is used to evaluate any empty editTexts
    boolean isEmpty(EditText Username) {
        CharSequence str = Username.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void GoToRegister(View view){
        Intent intent = new Intent(this, com.example.RegisterAndLoginPage.Register.class);
        startActivity(intent);
    }

    //Opening the Dashboard page when logged in
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


