package com.example.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Dashboard_Activity;
import com.example.test.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class login extends AppCompatActivity {

    // Declaration of variables (The IIE, 2022)
    EditText Username, Password, Email;
    Button LoginButton;
    private FirebaseAuth Auth;

    // Creation of Array List (The IIE, 2022)
    // public static ArrayList<LoginPageDataModel> userList = new ArrayList<LoginPageDataModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Auth = FirebaseAuth.getInstance();
/*
        if (userList.size() == 0){
            LoginPageDataModel TestUser = new LoginPageDataModel("Testing.com", "Password123");
            userList.add(TestUser);
        }
*/
        // Calling of methods (The IIE, 2022)
        setupUI();
        setupListener();
    }

    // This method retreives the data entered in each of the textboxes as well as the login button (The IIE, 2022)
    private void setupUI() {
        Email = findViewById((R.id.Email_EditText));
        //Username = findViewById(R.id.UsernameEditText);
        Password = findViewById(R.id.Password_EditText);
        LoginButton = findViewById(R.id.LoginButton);
    }

    // This method verifies the entered username (The IIE, 2022)
    private void setupListener() {
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (verifyUsername()) {
                signIn(Email.getText().toString(), Password.getText().toString());
                //Login(view);
                //   } else {
                //prompt if user makes a wong input on password or username (The IIE, 2022)
                //    Toast t = Toast.makeText(getApplicationContext(), "Wrong username or Password", Toast.LENGTH_SHORT);
                //   t.show();
            }

            // }
        });
    }

    //This method is used to verify the username and password (The IIE, 2022)

    /*public boolean verifyUsername() {

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
        return isInputValid;

     */

       /* boolean isUserValid = false;

        //This if statement is used to validate the username and password (The IIE, 2022)
        if (isInputValid) {
            String UsernameVal = Username.getText().toString();
            String PasswordVal = Password.getText().toString();
            //If statement evaluates if the password matches the username (The IIE, 2022)
            // for (LoginPageDataModel user: userList) {
            if (UsernameVal.equals(Username.getText()) && PasswordVal.equals(user.getPassword())) {
                isUserValid = true;
                return isUserValid;
            }

            return isUserValid;
        }
    }*/


    //boolean is used to evaluate any empty editTexts (The IIE, 2022)
    boolean isEmpty(EditText Username) {
        CharSequence str = Username.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void signIn(String email, String password) {
        Auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(login.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();

                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = Auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }




    private void updateUI(FirebaseUser user) {
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


