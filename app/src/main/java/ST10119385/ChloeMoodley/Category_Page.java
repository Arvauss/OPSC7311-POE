package ST10119385.ChloeMoodley;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.test.Dashboard_Activity;
import com.example.test.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;


public class Category_Page extends AppCompatActivity{

    private final int STORAGE_PERMISSION_CODE = 100;
    private final int CAMERA_PERMISSION_CODE = 101;
    ImageView image;
    EditText CategoryName;
    EditText CategoryDescription;
    Spinner Colour;
    Button btnConfirmCategory;
    Category_Information Catobj;
    Bitmap img;
    Uri imgURI;

    private static final int REQUEST_IMAGE_CAPTURE = 0;

    private DatabaseReference dbRef;
    private FirebaseStorage fbStorage;
    private StorageReference storRef;
    private FirebaseAuth Auth;
    FirebaseUser user;



    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView burgerNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_ui_page);

        dbRef = FirebaseDatabase.getInstance("https://bodegaapp-opscpoe-default-rtdb.firebaseio.com/").getReference();
        fbStorage = FirebaseStorage.getInstance();
        storRef = fbStorage.getReference();
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();

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
        Spinner dropDown = (Spinner) findViewById(R.id.DropDown);   //find spinner by the id (The IIE, 2022)


        //array adapter created to get information from the string array in the string view (The IIE, 2022)
        ArrayAdapter<String> myAdapt = new ArrayAdapter<String>(Category_Page.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Colours));   //finding the array and layout of the dropdown menu

        myAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //making the adapter a dropdown
        dropDown.setAdapter(myAdapt);   //setting the spinner to the adapter

        setUpUI();
        setUpListener();
    }

    // The code below sets the UI so that each elements data is stored (The IIE, 2022)
    private void setUpUI() {

        image = (ImageView) findViewById(R.id.ImageCat);
        btnConfirmCategory = (Button) findViewById(R.id.CategoryConfirm);
        CategoryName = (EditText) findViewById(R.id.NameTextBox);
        CategoryDescription = (EditText) findViewById(R.id.DescriptionTextBox);
        Colour = (Spinner) findViewById(R.id.DropDown);
    };

    //AR Launcher (The IIE, 2022)
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Bundle imgBundle = result.getData().getExtras();
            if (imgBundle != null){
                img = (Bitmap) imgBundle.get("data");
                image.setImageBitmap(img);

                //gets timestamp at image creation, for use as image ID in database (Android Developers, 2022)
                Long timestamp = System.currentTimeMillis()/1000;
                String imgID = "IMG" + timestamp.toString();

                ByteArrayOutputStream boas = new ByteArrayOutputStream();
                img.compress(Bitmap.CompressFormat.JPEG, 100, boas);
                byte[] imgData = boas.toByteArray();
                String imgPath = MediaStore.Images.Media.insertImage(getContentResolver(), img, imgID, null);
                imgURI = Uri.parse(imgPath);

                uploadImg(imgID);

            }
        }
    });

    private void uploadImg(String imgid) {
        StorageReference imgRef = storRef.child("images/" + imgid);

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading image");
        pd.show();

        imgRef.putFile(imgURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Category_Page.this, "Image uploaded", Toast.LENGTH_SHORT).show();
                pd.dismiss();
                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imgURI = uri;
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            Toast.makeText(Category_Page.this, "Image upload failed", Toast.LENGTH_LONG).show();
            pd.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Percentage: " + (int) progress + "%");
            }
        });
    }

    // The code below allows a user to choose an image for the category (The IIE, 2022)
    private void setUpListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.ImageCat) {
                 //   checkPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
                    checkPermissions(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
                }else{

                }

            }
        });

        btnConfirmCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets timestamp at category creation, for use as category ID in database (Android Developers, 2022)
                Long timestamp = System.currentTimeMillis()/1000;
                String catID = "CAT" + timestamp.toString();
                //gets String array based on array of colours, matches index of colours with dropdown list selected item to get colour (The IIE, 2022)
                //(Danylyk D., 2012)  https://stackoverflow.com/questions/9114587/how-can-i-save-colors-in-array-xml-and-get-it-back-to-a-color-array
                String[] ColoursList = getApplicationContext().getResources().getStringArray(R.array.clrs);
                Catobj = new Category_Information(catID,
                        Color.parseColor(ColoursList[Colour.getSelectedItemPosition()]),
                        CategoryName.getText().toString(),
                        CategoryDescription.getText().toString(),
                        imgURI.toString(),
                        user.getUid());

                Dashboard_Activity.catList.add(Catobj);


                //adds new category object to database, with identifier being timestamp at creation (Firebase, 2022)
                dbRef.child("categories").child(Catobj.getCatID()).setValue(Catobj);
                GoBackDash(view);
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
            ImageHandler();
        }
    }

    public void ImageHandler(){
        Toast.makeText(Category_Page.this, "Camera permission granted", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        resultLauncher.launch(i);
    }


//    android on click = view

    //Method to handle the OnCLicked events within the burger menu (Pulak, 2017)
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            //Go to dashboard (The IIE, 2022)
            Intent dashB = new Intent(this, Dashboard_Activity.class);
            startActivity(dashB);
        } else if (id == R.id.nav_shopping_list){
            //Go to shopping list page (The IIE, 2022)
            Intent shopList = new Intent(this, ShoppingList_Page.class);
            startActivity(shopList);
        } else if (id == R.id.nav_graph_screen) {
            //Go to Graph page
            Intent graphPage = new Intent(this, Graph_Page.class);
            startActivity(graphPage);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // override the onOptionsItemSelected (The IIE, 2022)
    // function to implement (The IIE, 2022)
    // the item click listener callback (The IIE, 2022)
    // to open and close the navigation (The IIE, 2022)
    // drawer when the icon is clicked (geeksforgeeks.org, 2022).
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//android on click = view (The IIE, 2022)
    public void GoBackDash (View v) {
        Intent goBackDash = new Intent (this, Dashboard_Activity.class);
        startActivity(goBackDash);
    }
}
