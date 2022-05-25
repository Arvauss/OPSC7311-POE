package ST10119385.ChloeMoodley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class Add_Item_Page extends AppCompatActivity {

    //adding second view and class (Add a Second Activity to your App, 2017).
    private static final String TAG = "MainActivity2";

    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;     //(Android Beginner Tutorial #25- DatePicker Dialog [Choosing a Date from a Dialog Pop-Up], 2017)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_ui_page);

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
}