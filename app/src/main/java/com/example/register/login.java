package com.example.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Auth = FirebaseAuth.getInstance();

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
                if (verifyUsername()) {
                    signIn(Email.getText().toString(), Password.getText().toString());
                       } else {
                    //prompt if user makes a wong input on password or username (The IIE, 2022)
                        Toast t = Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT);
                       t.show();
                }

            }
        });
    }

    //boolean is used to evaluate any empty editTexts (The IIE, 2022)
    boolean isEmpty (EditText Username){
        CharSequence str = Username.getText().toString();
        return TextUtils.isEmpty(str);
    }

    //boolean is used to ensure that the email is correct
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    //This method is used to verify the username and password (The IIE, 2022)
    boolean verifyUsername() {

        //Method uses a boolean to check if the enter data is eiter true or false (The IIE, 2022)
        boolean isInputValid = true;

        //If statement used to show an error message if the username text is empty (The IIE, 2022)
        if (isEmail(Email)==false) {
            Email.setError("Enter a valid email!");
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
    }

//This method is used to sign in a user with the correct login details
        public void signIn (String email, String password){
            Auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(login.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Dashboard_Activity.class);
                                startActivity(intent);
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = Auth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(login.this, "Authentication failed. User not found", Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        }

        private void updateUI (FirebaseUser user){
        }
        // This redirets the user to the register page (The IIE, 2022)
        public void GoToRegister (View view){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }


    }



