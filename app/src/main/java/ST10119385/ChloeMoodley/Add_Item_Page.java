package ST10119385.ChloeMoodley;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import opscwork.viewitempagefeatures.ItemPage;

public class Add_Item_Page extends AppCompatActivity {

    // Declaration of variables (The IIE, 2022)
    private final int STORAGE_PERMISSION_CODE = 100;
    private final int CAMERA_PERMISSION_CODE = 101;
    ImageView picture;
    EditText ItemName;
    EditText ItemDescription;
    TextView ItemPurchaseDate;
    EditText ItemPrice;
    Button btnItemConfirm;

    Item_Information obj;
    Bitmap imageBMP;
    Uri imgUri;


    //adding second view and class (Add a Second Activity to your App, 2017).
    private static final String TAG = "MainActivity2";

    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;     //(Android Beginner Tutorial #25- DatePicker Dialog [Choosing a Date from a Dialog Pop-Up], 2017)

    //Declarations for DrawerLayout (geeksforgeeks.org, 2022)
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView burgerNavigationView;

    private DatabaseReference dbRef;
    private FirebaseStorage fbStorage;
    private StorageReference storRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_ui_page);

        dbRef = FirebaseDatabase.getInstance("https://bodegaapp-opscpoe-default-rtdb.firebaseio.com/").getReference();
        fbStorage = FirebaseStorage.getInstance();
        storRef = fbStorage.getReference();

        setUpUI();
        setUpListener();

        // drawer layout instance to toggle the menu icon to open (The IIE, 2022)
        //drawer and back button to close drawer (geeksforgeeks.org, 2022).
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        //Instantiating burgerNavigationView and binding it to view (Pulak, 2017).
        burgerNavigationView = findViewById(R.id.burgerNavigationView);

        //Setting navigation item listener (Pulak, 2017).
        burgerNavigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        // pass the Open and Close toggle for the drawer layout listener (The IIE, 2022)
        // to toggle the button (geeksforgeeks.org, 2022).
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar (geeksforgeeks.org, 2022).
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* the datapicker code to allow user to select year, month and day without (The IIE, 2022)
        typing it (Android Beginner Tutorial #25  - DatePicker Dialog [Choosing a Date from a Dialog Pop-Up], 2017). */

        displayDate = (TextView) findViewById(R.id.DatePicker); //finding view by the text id

        /*
            When user clicks year, month, and day in a calender format
            will pop up mid page when view goes to the activity_main2 view (The IIE, 2022)
         */
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);  //year will display (The IIE, 2022)
                int month = cal.get(Calendar.MONTH);    //month will display (The IIE, 2022)
                int day = cal.get(Calendar.DAY_OF_MONTH); //day of month will display (The IIE, 2022)

                //already installed in design of calender (The IIE, 2022)
                DatePickerDialog di = new DatePickerDialog (Add_Item_Page.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener,
                        year, month, day);

                //color of calender (The IIE, 2022)
                di.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                di.show();
            }
        });

        //month is automatically set a 0, when month displays we need to add one to make it the month the user selects (The IIE, 2022)
        //automatically set it to today's date (The IIE, 2022)
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " +month + "/" + day + "/" + year);

                String date = month + "/" +day + "/" +year;
                displayDate.setText(date);
            }
        };
    }

    // The code below sets up the UI in order for all the data entered to be stored in each variable (The IIE, 2022)
    private void setUpUI() {
        ItemName = (EditText) findViewById(R.id.ItemNameBox);
        ItemDescription = (EditText) findViewById(R.id.ItemDescTextBox);
        ItemPurchaseDate = (TextView) findViewById(R.id.DatePicker);
        ItemPrice = (EditText) findViewById(R.id.priceTextBox);
        picture = (ImageView) findViewById(R.id.ImageItemPic);
        btnItemConfirm = (Button) findViewById(R.id.itemConfirm);
    }

    //AR Launcher (The IIE, 2022)
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Bundle imgBundle = result.getData().getExtras();
            if (imgBundle != null){
                imageBMP = (Bitmap) imgBundle.get("data");
                picture.setImageBitmap(imageBMP);

                //gets timestamp at image creation, for use as image ID in database (Android Developers, 2022)
                Long timestamp = System.currentTimeMillis()/1000;
                String imgID = "IMG" + timestamp.toString();

                ByteArrayOutputStream boas = new ByteArrayOutputStream();
                imageBMP.compress(Bitmap.CompressFormat.JPEG, 100, boas);
                byte[] imgData = boas.toByteArray();
                String imgPath = MediaStore.Images.Media.insertImage(getContentResolver(), imageBMP, imgID, null);
                imgUri = Uri.parse(imgPath);

                uploadImg(imgID);
            }
        }
    });

    private void uploadImg(String imgid) {
        StorageReference imgRef = storRef.child("images/" + imgid);

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading image");
        pd.show();

        imgRef.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Add_Item_Page.this, "Image uploaded", Toast.LENGTH_SHORT).show();
                pd.dismiss();
                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imgUri = uri;
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Add_Item_Page.this, "Image upload failed", Toast.LENGTH_LONG).show();
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

    //Method to handle the OnCLicked events within the burger menu (Pulak, 2017)
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here (The IIE, 2022)
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
    private void setUpListener() {
        picture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.ImageItemPic) {
                    //checkPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
                    checkPermissions(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
                }else{
                }
            }
        });

        // Variable Declaration (The IIE, 2022)
        String catName;
        String catid;
        Intent PrevoiusIntent = getIntent();
        catName = PrevoiusIntent.getStringExtra("catName");
        catid = PrevoiusIntent.getStringExtra("catID");

        btnItemConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets timestamp at category creation, for use as category ID in database (Android Developers, 2022)
                Long timestamp = System.currentTimeMillis()/1000;
                String itmID = "ITM" + timestamp.toString();

                obj = new Item_Information(itmID,
                        ItemName.getText().toString(),
                        ItemDescription.getText().toString(),
                        ItemPurchaseDate.getText().toString(),
                        Double.parseDouble(ItemPrice.getText().toString()),
                        catName,
                        catid,
                        1,
                        imgUri.toString()
                );
                int position = 0;
                for (Category_Information cat: Dashboard_Activity.catList) {
                    if (cat.getCategory_Name().equals(obj.getCategory())){
                        position = Dashboard_Activity.catList.indexOf(cat);
                        break;
                    }
                }
                ItemPage.ItemArrayList.add(obj);

                //adds new item object to database, with identifier being timestamp at creation (Firebase, 2022)
                dbRef.child("items").child(obj.getItem_ID()).setValue(obj);
                goBackToList(view, position);

            }
        });
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

    // The code below checks for permission before the user can choose an image (The IIE, 2022)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Add_Item_Page.this, "Camera permisison granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Add_Item_Page.this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Add_Item_Page.this, "Storage permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Add_Item_Page.this, "Storage permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // The code below checks for permission before the user can choose an image (The IIE, 2022)
        public void checkPermissions(String permission, int requestCode) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Add_Item_Page.this, new String[]{permission}, requestCode);
            } else {
                Toast.makeText(Add_Item_Page.this, "Permission already granted", Toast.LENGTH_SHORT).show();
                ImageHandler();
            }
        }

    // The code below ensures a user can add an image (The IIE, 2022)
    public void ImageHandler(){
        Toast.makeText(Add_Item_Page.this, "Camera permission granted", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        resultLauncher.launch(i);
    }

    // The code below takes the user to the dashboard (The IIE, 2022)
    public void goBackToList (View v, int pos) {

        Intent listBackItem = new Intent(getApplicationContext(), Dashboard_Activity.class);
        listBackItem.putExtra("id", pos);
        setResult(1);
        startActivity(listBackItem);
    }
}