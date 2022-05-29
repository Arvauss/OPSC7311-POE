package ST10119385.ChloeMoodley;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

import java.util.ArrayList;

public class ShoppingList_Page extends AppCompatActivity {

    private static final String TAG ="ShoppingList_Page";
    TextView ItemNameShoppingList;
    TextView CategoryNameShoppingList;
    ImageView ItemImageShoppingList;
    TextView ItemPriceShoppingList;
    TextView ItemQuantityShoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list_page);

        Log.d(TAG, "onCreate : Started.");
        ListView mList = (ListView) findViewById(R.id.shoppingListView);

        ItemNameShoppingList = (TextView) findViewById(R.id.ItemViewName);
        CategoryNameShoppingList = (TextView) findViewById(R.id.VegetablesTextView);
        ItemImageShoppingList = (ImageView) findViewById(R.id.LettuceImage);
        ItemPriceShoppingList = (TextView) findViewById(R.id.Price);
        ItemQuantityShoppingList = (TextView) findViewById(R.id.NumOfItems);

        //object made
        Item_Information slo = new Item_Information(ItemNameShoppingList.toString(),int.class.cast(ItemImageShoppingList),
                double.class.cast(ItemPriceShoppingList), CategoryNameShoppingList.toString(), int.class.cast(ItemQuantityShoppingList));

        //add to list
        ArrayList<Item_Information> ShoppingListArrayList = new ArrayList<>();

        ShoppingListArrayList.add((slo));

        ShoppingList_Adapter adp = new ShoppingList_Adapter(this, R.layout.shopping_list_template, ShoppingListArrayList);
        mList.setAdapter(adp);

    }
}