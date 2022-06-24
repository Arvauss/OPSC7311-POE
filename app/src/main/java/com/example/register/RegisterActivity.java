package com.example.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    // Declaration of variables (The IIE, 2022)
    EditText username_EditText, password_EditText, email_EditText;
    Button btn_register;
    TextView login_link;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Auth = FirebaseAuth.getInstance();
        // Storing data from different elements into the variables (The IIE, 2022)
        email_EditText = (EditText) findViewById(R.id.RegEmailEditText);
        username_EditText = (EditText) findViewById(R.id.RegUsernameEditText);
        password_EditText = (EditText) findViewById(R.id.RegPasswordEditText);
        btn_register = (Button) findViewById(R.id.RegisterButton);
        login_link = (TextView) findViewById(R.id.LoginHyperLink);

        // This is a click method when the register button is clicked (The IIE, 2022)
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDataEntered()) {
                    createAccount(email_EditText.getText().toString(), password_EditText.getText().toString(), view);
                    //LoginPageDataModel NewUser = new LoginPageDataModel(username.getText().toString(), password.getText().toString());
                    //login.userList.add(NewUser);
                    //Toast t = Toast.makeText(getApplicationContext(), "New user created", Toast.LENGTH_SHORT);
                    //t.show();
                  //  Intent intent = new Intent(getApplicationContext(), login.class);
                    //intent.putExtra("username", username.toString());
                    //intent.putExtra("password", password.toString());
                  //  startActivity(intent);
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
//Method to check if email inserted is correct
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    // This method checks if the textboxes are empty (The IIE, 2022)
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    // This method forces the user to enter a username and password (The IIE, 2022)
    boolean checkDataEntered() {
        //Boolean method is used to check if the data a user enters is correct
        boolean dataValid = true;

        if (isEmail(email_EditText)==false) {
            email_EditText.setError("Enter a valid email!");
            dataValid = false;
        }
        if (isEmpty(username_EditText)) {
            Toast t = Toast.makeText(this, "You must enter a username", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        if (isEmpty(password_EditText)) {
            password_EditText.setError("Password is required");
            dataValid = false;
        }
        return dataValid;
    }


    //method is used to create an account
    private void createAccount(String email, String password, View view) {
        //create user with email
        Auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String Username = username_EditText.getText().toString();
                            LoginPageDataModel accountUser = new LoginPageDataModel(Username,email,password);
                            // Registration success, update UI with the signed-in user's information
                            FirebaseUser firebaseUser = Auth.getCurrentUser();


                            Toast.makeText(RegisterActivity.this, "User Created"+ firebaseUser.getEmail(), Toast.LENGTH_LONG).show();
                            GoToLogin(view);
                        } else {
                            // If registration fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Registration failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
