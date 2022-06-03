package ST10119385.ChloeMoodley;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.test.Dashboard_Activity;
import com.example.test.R;
import com.google.android.material.navigation.NavigationView;


public class Category_Page extends AppCompatActivity{

    private final int STORAGE_PERMISSION_CODE = 100;
    private final int CAMERA_PERMISSION_CODE = 101;
    ImageView image;
    private static final int REQUEST_IMAGE_CAPTURE = 0;

    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView burgerNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_ui_page);

        // drawer layout instance to toggle the menu icon to open
        //drawer and back button to close drawer (geeksforgeeks.org, 2022).
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        //Instantiating burgerNavigationView and binding it to view (Pulak, 2017).
        burgerNavigationView = findViewById(R.id.burgerNavigationView);

        //Setting navigation item listener (Pulak, 2017).
        burgerNavigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button (geeksforgeeks.org, 2022).
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar (geeksforgeeks.org, 2022).
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
            adapter made to push data (in the array string ) from the strings view to the
            activity_main view so all options display on UI (Android Drop Down List Tutorial, 2016).
         */
        Spinner dropDown = (Spinner) findViewById(R.id.DropDown);   //find spinner by the id

        Button but = (Button) findViewById(R.id.itemConfirm);

        //array adapter created to get information from the string array in the string view
        ArrayAdapter<String> myAdapt = new ArrayAdapter<String>(Category_Page.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Colours));   //finding the array and layout of the dropdown menu

        myAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //making the adapter a dropdown
        dropDown.setAdapter(myAdapt);   //setting the spinner to the adapter

        setUpUI();
       // setUpListener();
    }

    private void setUpUI() {

        ImageView image = (ImageView) findViewById(R.id.ImageCat);
    }

    private void setUpListener() {
        image.setOnClickListener(new View.OnClickListener() {

            ActivityResultLauncher<Intent> acivityResultLauncher = null;
            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.ImageCat) {
                    checkPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
                    checkPermissions(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
                }else{
                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    acivityResultLauncher.launch(i);
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(Category_Page.this, "Camera permission granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Category_Page.this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == STORAGE_PERMISSION_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(Category_Page.this, "Storage permission granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(Category_Page.this, "Storage permission denied", Toast.LENGTH_SHORT).show();
            }
    }
}
    public void checkPermissions(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Category_Page.this, new String[]{permission}, requestCode);
        } else {
            Toast.makeText(Category_Page.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }
    public void onActivityResult(ActivityResult result){
        Uri imageuri;
        Bundle bundle = result.getData().getExtras();
        Bitmap imageBitMap = (Bitmap) bundle.get("Data");
        image.setImageBitmap(imageBitMap);
    }

//    android on click = view

    //Method to handle the OnCLicked events within the burger menu (Pulak, 2017)
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            //Go to dashboard
            Intent dashB = new Intent(this, Dashboard_Activity.class);
            startActivity(dashB);
        } else if (id == R.id.nav_shopping_list){
            //Go to shopping list page
            Intent shopList = new Intent(this, ShoppingList_Page.class);
            startActivity(shopList);
        } else if (id == R.id.nav_graph_screen) {
            //Go to Graph page
            //   Intent graphPage = new Intent(this, ShoppingList_Page.class);
            //   startActivity(graphPage);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked (geeksforgeeks.org, 2022).
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//android on click = view
    public void GoBackDash (View v) {
        Intent goBackDash = new Intent (this, Dashboard_Activity.class);
        startActivity(goBackDash);
    }
}
