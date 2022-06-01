package com.example.DataModels;

public class User_Information {
    // This class is the data model for the Login Page (The IIE, 2022)


        // Declaration of attributes (The IIE, 2022)
        private String Username;
        private String Password;

        // This constructor accepts two string inputs for the username and password (The IIE, 2022)
        public User_Information(String username, String password) {
            Username = username;
            Password = password;
        }

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


