package ST10119385.ChloeMoodley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import opscwork.viewitempagefeatures.R;

//import com.example.test.R;
//import android.widget.Toastimport androidx.appcompat.app.AppCompatActivity;


public class Category_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_ui_page);

        /*
            adapter made to push data (in the array string ) from the strings view to the
            activity_main view so all options display on UI (Android Drop Down List Tutorial, 2016).
         */
        Spinner dropDown = (Spinner) findViewById(R.id.DropDown);   //find spinner by the id

        //array adapter created to get information from the string array in the string view
        ArrayAdapter<String> myAdapt = new ArrayAdapter<String>(Category_Page.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Colours));   //finding the array and layout of the dropdown menu

        myAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //making the adapter a dropdown
        dropDown.setAdapter(myAdapt);   //setting the spinner to the adapter
    }
//android on click = view
//    public void  MainActivity2 (View v)
//    {
//        Intent i = new Intent(this, Add_Item_Page.class);
//        startActivity(i);
//
//    }
}
