package opscwork.viewitempagefeatures;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ST10119385.ChloeMoodley.Item_Information;

public class ViewItem extends AppCompatActivity {

    //Adding array list
    public static ArrayList<Item_Information> itemArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item_page);

    }
}