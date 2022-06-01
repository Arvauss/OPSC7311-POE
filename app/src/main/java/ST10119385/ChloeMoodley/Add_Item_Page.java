package ST10119385.ChloeMoodley;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
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

import java.util.Calendar;

public class Add_Item_Page extends AppCompatActivity {

    private final int STORAGE_PERMISSION_CODE = 100;
    private final int CAMERA_PERMISSION_CODE = 101;
    ImageView picture;

    //adding second view and class (Add a Second Activity to your App, 2017).
    private static final String TAG = "MainActivity2";

    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;     //(Android Beginner Tutorial #25- DatePicker Dialog [Choosing a Date from a Dialog Pop-Up], 2017)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_ui_page);
        setUpUI();
        setUpListener();

        /* the datapicker code to allow user to select year, month and day without
        typing it (Android Beginner Tutorial #25  - DatePicker Dialog [Choosing a Date from a Dialog Pop-Up], 2017). */

        displayDate = (TextView) findViewById(R.id.DatePicker); //finding view by the text id
        /*
            When user clicks year, month, and day in a calender format
            will pop up mid page when view goes to the activity_main2 view.
         */
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);  //year will display
                int month = cal.get(Calendar.MONTH);    //month will display
                int day = cal.get(Calendar.DAY_OF_MONTH); //day of month will display

                //already installed in design of calender
                DatePickerDialog di = new DatePickerDialog (Add_Item_Page.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener,
                        year, month, day);

                //color of calender
                di.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                di.show();
            }
        });

        //month is automatically set a 0, when month displays we need to add one to make it the month the user selects
        //automatically set it to today's date
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

    private void setUpUI() {
        ImageView picture = (ImageView) findViewById(R.id.ImageItemPic);
    }

    private void setUpListener() {
        picture.setOnClickListener(new View.OnClickListener() {

            ActivityResultLauncher<Intent> acivityResultLauncher = null;
            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.ImageItemPic) {
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

        public void checkPermissions(String permission, int requestCode) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Add_Item_Page.this, new String[]{permission}, requestCode);
            } else {
                Toast.makeText(Add_Item_Page.this, "Permission already granted", Toast.LENGTH_SHORT).show();

            }
        }
    public void onActivityResult(ActivityResult result){
        Uri imageuri;
        Bundle bundle = result.getData().getExtras();
        Bitmap imageBitMap = (Bitmap) bundle.get("Data");
        picture.setImageBitmap(imageBitMap);
    }

        public void goBackToList (View v) {
        Intent listBackItem = new Intent(this, Dashboard_Activity.class);
        startActivity(listBackItem);
    }
}