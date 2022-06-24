package com.example.register;

public class LoginPageDataModel {
    // This class is the data model for the Login Page (The IIE, 2022)


        // Declaration of attributes (The IIE, 2022)
        private String Email;
        private String Username;
        private String Password;

        // This constructor accepts two string inputs for the username and password (The IIE, 2022)
        public LoginPageDataModel(String email, String username, String password) {
            this.Email = email;
            this.Username = username;
            this.Password = password;
        }

public LoginPageDataModel(){

}
    public String getEmail() {return Email;}

    // The following is a set method to set the username (The IIE, 2022)
    public void setEmail(String email) {Email = email;}

    // The following is a get method to retrieve the username (The IIE, 2022)
        public String getUsername() {
            return Username;
        }

        // The following is a set method to set the username (The IIE, 2022)
        public void setUsername(String username) {
            Username = username;
        }

        // The following is a get method to retrieve the password (The IIE, 2022)
        public String getPassword() {
            return Password;
        }

        // The following is a set method to set the password (The IIE, 2022)
        public void setPassword(String password) {
            Password = password;
        }
    }


