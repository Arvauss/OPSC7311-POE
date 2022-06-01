package ST10119385.ChloeMoodley;

import static android.Manifest.*;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.register.R;
import com.example.test.Dashboard_Activity;
 import com.example.test.R;
//import android.widget.Toastimport androidx.appcompat.app.AppCompatActivity;

public abstract class Category_Page extends AppCompatActivity implements View.OnClickListener{

    private final int STORAGE_PERMISSION_CODE = 100;
    private final int CAMERA_PERMISSION_CODE = 101;
    ImageView image;
    private static final int REQUEST_IMAGE_CAPTURE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_ui_page);
        setUpUI();
        setUpListener();

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
                    checkPermissions(permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
                    checkPermissions(permission.CAMERA, CAMERA_PERMISSION_CODE);
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

    //android on click = view
    public void GoBackDash (View v) {
        Intent goBackDash = new Intent (this, Dashboard_Activity.class);
        startActivity(goBackDash);
    }


}

