package ST10119385.ChloeMoodley;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

import java.util.ArrayList;

public class ShoppingList_Page extends AppCompatActivity {

    private static final String TAG ="ShoppingList_Page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list_page);

        Log.d(TAG, "onCreate : Started.");
        ListView mList = (ListView) findViewById(R.id.shoppingListView);

        //add to list
        ArrayList<Item_Information> ShoppingListArrayList = new ArrayList<>();


//        ShoppingListArrayList.add(findViewById(R.id.))
//
//        ShoppingList_Adapter adp = new ShoppingList_Adapter(this, R.layout.shopping_list_page, ShoppingListArrayList);
//        mList.setAdapter(adp);

    }
}